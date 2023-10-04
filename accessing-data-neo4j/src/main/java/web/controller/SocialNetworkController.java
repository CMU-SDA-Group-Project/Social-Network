package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.service.impl.SocialNetworkImpl;

/**
 * @author yunjia
 */
@RestController
@RequestMapping("/social")
public class SocialNetworkController {

    @Autowired
    public SocialNetworkImpl socialNetwork;

    @PostMapping("/createUser")
    public String createUser() {
        socialNetwork.createUser(null);
        return "createUser";
    }

}
