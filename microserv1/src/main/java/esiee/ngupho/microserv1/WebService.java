package esiee.ngupho.microserv1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import esiee.ngupho.microserv1.dto.CurrentWeatherDTO;

@RestController
public class WebService {

    @Value("${weatherAPIKey}")
    String apiKey;

    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public ResponseEntity<CurrentWeatherDTO> current(@RequestParam String location){
        CurrentWeatherDTO currentWeatherDTO = new CurrentWeatherDTO();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String data = restTemplate.getForObject("http://api.weatherapi.com/v1/current.json?lang=fr&key=" + apiKey
                    + "&q=" + location, String.class);

            // Use Jackson to convert String to JSON Object
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(data);

            // Getting the location
            JsonNode name = root.get("location").get("name");
            JsonNode region = root.get("location").get("region");
            JsonNode country = root.get("location").get("country");

            // Getting data
            JsonNode temperature = root.get("current").get("temp_c");
            JsonNode humidity = root.get("current").get("humidity");
            JsonNode wind = root.get("current").get("wind_kph");
            JsonNode txt = root.get("current").get("condition").get("text");

            currentWeatherDTO.setLocation(name.textValue() + ", " + region.textValue() + ", " + country.textValue());
            currentWeatherDTO.setTemperature(temperature.asDouble());
            currentWeatherDTO.setHumidity(humidity.asInt());
            currentWeatherDTO.setWind(wind.asDouble());
            currentWeatherDTO.setText(txt.textValue());

            System.out.println(currentWeatherDTO);
            System.out.println(data);
            return new ResponseEntity<CurrentWeatherDTO>(currentWeatherDTO,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<CurrentWeatherDTO>(currentWeatherDTO,HttpStatus.BAD_REQUEST);
        }
    }
}