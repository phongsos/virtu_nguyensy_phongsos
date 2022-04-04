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
    @RequestMapping(path = "/status", method = RequestMethod.GET)
    public String status(){
        return "MS2 is running !";
    }

    /**
     * Returns a picture according to a tag
     * @param tag
     * @return
     */
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public ResponseEntity<PhotoDTO> search(@RequestParam String tag){
        String QWANT_LOCALE = "fr_fr";
        String QWANT_SAFESEARCH = "1";

        PhotoDTO photoDTO = new PhotoDTO();

        try {
            RestTemplate restTemplate = new RestTemplate();
            String data = restTemplate.getForObject("https://api.qwant.com/v3/search/images"
                    + "?locale=" + QWANT_LOCALE
                    + "&safesearch=" + QWANT_SAFESEARCH
                    + "&q=" + tag
                    , String.class);

            // Use Jackson to convert String to JSON Object
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(data);

            // Getting data
            JsonNode id = root.get("data").get("result").get("items").get(0).get("_id");
            //JsonNode owner = "N/A";
            JsonNode title = root.get("data").get("result").get("items").get(0).get("title");
            JsonNode source = root.get("data").get("result").get("items").get(0).get("media");

            // Setting the DTO
            photoDTO.setId(id.asLong());
            photoDTO.setOwner("N/A");
            photoDTO.setTitle(title.textValue());
            photoDTO.setSource(source.textValue());

            return new ResponseEntity<PhotoDTO>(photoDTO, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<PhotoDTO>(photoDTO, HttpStatus.BAD_REQUEST);
        }
    }

}