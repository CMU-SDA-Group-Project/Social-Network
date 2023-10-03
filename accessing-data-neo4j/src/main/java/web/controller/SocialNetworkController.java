package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.service.impl.SocialNetworkImpl;

@RestController
@RequestMapping("/social")
public class SocialNetworkController {

    @Autowired
    private SocialNetworkImpl socialNetwork;



}
