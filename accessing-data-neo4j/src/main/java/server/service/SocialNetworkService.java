package server.service;

import api.req.*;
import api.res.*;

public interface SocialNetworkService {

    CreateUserResponse createUser(CreateUserRequest request);
    DeleteUserResponse deleteUser(DeleteUserRequest request);
    AddFriendResponse addFriend(AddFriendRequest request);
    RemoveFriendResponse removeFriend(RemoveFriendRequest request);
    GetFriendsResponse getFriends(GetFriendsRequest request);
}