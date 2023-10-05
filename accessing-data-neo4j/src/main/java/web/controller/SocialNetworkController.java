package web.controller;

import api.req.AddFriendRequest;
import api.req.CreateUserRequest;
import api.req.DeleteUserRequest;
import api.req.RemoveFriendRequest;
import api.res.AddFriendResponse;
import api.res.CreateUserResponse;
import api.res.DeleteUserResponse;
import api.res.RemoveFriendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.service.impl.SocialNetworkImpl;

import javax.validation.Valid;

/**
 * @author yunjia
 */
@RestController
@RequestMapping("/social")
public class SocialNetworkController {

    @Autowired
    public SocialNetworkImpl socialNetwork;

    @PostMapping("/createUser")
    public CreateUserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        return socialNetwork.createUser(request);
    }

    @PostMapping("/deleteUser")
    public DeleteUserResponse deleteUser(@Valid @RequestBody DeleteUserRequest request) {
        return socialNetwork.deleteUser(request);
    }

    @PostMapping("/addFriend")
    public AddFriendResponse addFriend(@Valid @RequestBody AddFriendRequest request) {
        return socialNetwork.addFriend(request);
    }

    @PostMapping("/removeFriend")
    public RemoveFriendResponse removeFriend(@Valid @RequestBody RemoveFriendRequest request) {
        return socialNetwork.removeFriend(request);
    }

}
