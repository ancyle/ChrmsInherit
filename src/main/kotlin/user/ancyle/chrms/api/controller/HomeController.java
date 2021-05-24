package user.ancyle.chrms.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(name="/",method = RequestMethod.GET)
    public String home(){
        System.gc();
        return "/pst/all";
    }
}
