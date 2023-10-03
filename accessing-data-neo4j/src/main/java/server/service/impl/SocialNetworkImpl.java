package server.service.impl;

import api.req.*;
import api.res.*;
import org.springframework.stereotype.Service;
import server.service.SocialNetworkService;

@Service
public class SocialNetworkImpl implements SocialNetworkService {

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        return null;
    }

    @Override
    public DeleteUserResponse deleteUser(DeleteUserRequest request) {
        return null;
    }

    @Override
    public AddFriendResponse addFriend(AddFriendRequest request) {
        return null;
    }

    @Override
    public RemoveFriendResponse removeFriend(RemoveFriendRequest request) {
        return null;
    }

    @Override
    public GetFriendsResponse getFriends(GetFriendsRequest request) {
        return null;
    }
}
