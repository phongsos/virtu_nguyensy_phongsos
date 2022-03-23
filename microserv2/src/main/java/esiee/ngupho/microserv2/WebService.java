package esiee.ngupho.microserv2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import esiee.ngupho.microserv2.dto.PhotoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebService {

//    @Value("${backEndURL}")
//    String backEndURL;

//    @RequestMapping(path = "/", method = RequestMethod.GET)
//    public String hello(){
//        try{
//            RestTemplate restTemplate = new RestTemplate();
//            String s = restTemplate.getForObject(backEndURL, String.class);
//            return "hello (from the front end)" + " " + s + " (from the back end)";
//        }catch (Exception e){
//            return e.getLocalizedMessage();
//        }
//    }

    @Value("${flickerAPIKey}")
    String apiKey;

    /**
     * Returns a picture according to a tag
     * @param tag
     * @return
     */
    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public ResponseEntity<PhotoDTO> current(@RequestParam String tag){
        PhotoDTO photoDTO = new PhotoDTO();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String data = restTemplate.getForObject("https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=" + apiKey + "&tags=" + tag
                    + "&extras=url_m,tags&format=json&nojsoncallback=1", String.class);

            // Use Jackson to convert String to JSON Object
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(data);

            // Getting data
            JsonNode id = root.get("photos").get("photo").get(0).get("id");
            JsonNode owner = root.get("photos").get("photo").get(0).get("owner");
            JsonNode title = root.get("photos").get("photo").get(0).get("title");

            // Setting the DTO
            photoDTO.setId(id.asLong());
            photoDTO.setOwner(owner.textValue());
            photoDTO.setTitle(title.textValue());

            return new ResponseEntity<PhotoDTO>(photoDTO, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<PhotoDTO>(photoDTO, HttpStatus.BAD_REQUEST);
        }
    }

}