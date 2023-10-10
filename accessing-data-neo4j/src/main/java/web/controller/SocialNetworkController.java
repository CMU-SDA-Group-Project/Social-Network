package web.controller;

import api.req.*;
import api.res.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/getFriends")
    public GetFriendsResponse getFriends(@Valid @RequestBody GetFriendsRequest request) {
        return socialNetwork.getFriends(request);
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("test");
        return "test";
    }

}
