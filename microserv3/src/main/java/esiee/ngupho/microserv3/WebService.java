package esiee.ngupho.microserv3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebService {

    @Value("${microserv1URL}")
    String microserv1URL;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String weatherOutfit() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String s = restTemplate.getForObject(microserv1URL, String.class);

            if(!s.isEmpty()) {
                return "Doesn't work :c";
            }
            else {
                return "It works!";
            }
        } catch (Exception e){
            return e.getLocalizedMessage();
        }
    }
}