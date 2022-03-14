package esiee.ngupho.microserv1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebService {

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String hello(){
        return " World !";
    }
}