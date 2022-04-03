package esiee.ngupho.microserv3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebService {

    @Value("${microserv1URL}")
    String microserv1URL;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String status(){
        try{
            RestTemplate restTemplate = new RestTemplate();
            String s = restTemplate.getForObject(microserv1URL + "/status", String.class);
            return "MS3 is running ! (from the MS3)" + " " + s + " (from MS1)";
        }catch (Exception e){
            return e.getLocalizedMessage();
        }
    }

    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public String weatherCurrentOutfit(@RequestParam String location) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String s = restTemplate.getForObject(microserv1URL + "/current?location=" + location, String.class);

            if(s.isEmpty()) {
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
