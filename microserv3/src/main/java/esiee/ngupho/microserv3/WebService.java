package esiee.ngupho.microserv3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import esiee.ngupho.microserv1.dto.CurrentWeatherDTO;
import esiee.ngupho.microserv1.utils.WeatherConstants;
import esiee.ngupho.microserv2.dto.PhotoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
public class WebService {

    @Value("${microserv1URL}")
    String microserv1URL;

    @Value("${microserv2URL}")
    String microserv2URL;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String status(){
        try{
            RestTemplate restTemplate = new RestTemplate();
            String s = restTemplate.getForObject(microserv1URL + "/status", String.class);
            String s2 = restTemplate.getForObject(microserv2URL + "/status", String.class);
            return "MS3 is running ! (from the MS3)\n" +
                    s2 + " (from MS2)\n"
                    + s + " (from MS1)";
        }catch (Exception e){
            return e.getLocalizedMessage();
        }
    }

    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<String>> weatherCurrentOutfit(@RequestParam String location) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String s = restTemplate.getForObject(microserv1URL + "/current?location=" + location, String.class);

            // Use Jackson to convert String to JSON Object
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(s);

            int temperature = root.get("temperature").asInt();
            String txt = root.get("text").textValue();

            ArrayList<String> photosList = new ArrayList<String>();

            if(WeatherConstants.TEXT_RAIN.find(txt)) {
                String tag = "parapluie png";

                s = restTemplate.getForObject(microserv2URL + "/search?tag=" + location, String.class);
                mapper = new ObjectMapper();
                root = mapper.readTree(s);

                String photoSource = root.get("source").textValue();

                photosList.add(photoSource);
            } else if(WeatherConstants.TEXT_SNOW.find(txt)) {
                String tag = "bonnet png";

                s = restTemplate.getForObject(microserv2URL + "/search?tag=" + location, String.class);
                mapper = new ObjectMapper();
                root = mapper.readTree(s);

                String photoSource = root.get("source").textValue();

                photosList.add(photoSource);
                tag = "gants png";

                s = restTemplate.getForObject(microserv2URL + "/search?tag=" + location, String.class);
                mapper = new ObjectMapper();
                root = mapper.readTree(s);

                photoSource = root.get("source").textValue();

                photosList.add(photoSource);
            } else if(WeatherConstants.TEXT_SUN.find(txt)) {
                String tag = "chapeau png";

                s = restTemplate.getForObject(microserv2URL + "/search?tag=" + location, String.class);
                mapper = new ObjectMapper();
                root = mapper.readTree(s);

                String photoSource = root.get("source").textValue();

                photosList.add(photoSource);
            }

            if(temperature <= 12) {
                String tag = "écharpe png";

                s = restTemplate.getForObject(microserv2URL + "/search?tag=" + location, String.class);
                mapper = new ObjectMapper();
                root = mapper.readTree(s);

                String photoSource = root.get("source").textValue();

                photosList.add(photoSource);
            }

            if(photosList.size() == 0) {
                String tag = "citation sourire";

                s = restTemplate.getForObject(microserv2URL + "/search?tag=" + location, String.class);
                mapper = new ObjectMapper();
                root = mapper.readTree(s);

                String photoSource = root.get("source").textValue();

                photosList.add(photoSource);
            }


            return new ResponseEntity<ArrayList<String>>(photosList, HttpStatus.OK);
        }  catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<ArrayList<String>>(photosList, HttpStatus.BAD_REQUEST);
        }
    }

}
