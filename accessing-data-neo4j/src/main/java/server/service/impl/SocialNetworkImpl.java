package server.service.impl;

import api.req.*;
import api.res.*;
import core.Friend;
import core.FriendRepository;
import core.Person;
import core.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.service.SocialNetworkService;
import web.SocialNetworkApplication;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author yunjia
 */
@Service
public class SocialNetworkImpl implements SocialNetworkService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FriendRepository friendRepository;

    private final static Logger log = LoggerFactory.getLogger(SocialNetworkApplication.class);

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {

        System.out.println("createUser");
        //personRepository.deleteAll();

        CreateUserResponse response = new CreateUserResponse();

        // Names should be unique
        try {
            if (personRepository.findByUserId(request.getUserId()) != null) {
                throw new Exception("user already exists");
            }

            //TODO check if user already exists:userId
            Person greg = Person.builder().userId(request.getUserId()).name(request.getName()).build();
            personRepository.save(greg);
            response.setSuccess(true);
            System.out.println(personRepository.findAll());

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public DeleteUserResponse deleteUser(DeleteUserRequest request) {

        System.out.println("deleteUser");
        DeleteUserResponse response = new DeleteUserResponse();

        try {
            Person person = personRepository.findByUserId(request.getUserId());
            if (person == null) {
                throw new Exception("user not found");
            }
            personRepository.delete(person);
            response.setSuccess(true);
            System.out.println(personRepository.findAll());

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @Override
    public AddFriendResponse addFriend(AddFriendRequest request) {
        System.out.println("addFriend");
        AddFriendResponse response = new AddFriendResponse();

        try {
            //TODO check if user already exists:userId
            Person person = personRepository.findByUserId(request.getUserId());
            if (person == null) {
                throw new Exception("user not found");
            }
            Person friend = personRepository.findByUserId(request.getFriendId());
            if (friend == null) {
                throw new Exception("friend not found");
            }
            Friend friend1 = Friend.builder().person(person).friend(friend).build();
            Friend friend2 = Friend.builder().person(friend).friend(person).build();
            friendRepository.save(friend1);
            friendRepository.save(friend2);

            response.setSuccess(true);
            System.out.println(friendRepository.findAll());

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return response;

    }

    @Override
    @Transactional
    public RemoveFriendResponse removeFriend(RemoveFriendRequest request) {
        System.out.println("removeFriend");
        RemoveFriendResponse response = new RemoveFriendResponse();

        try {
            //TODO check if user already exists:userId

            Person person = personRepository.findByUserId(request.getUserId());
            if (person == null) {
                throw new Exception("user not found");
            }
            Person friend = personRepository.findByUserId(request.getFriendId());
            if (friend == null) {
                throw new Exception("friend not found");
            }


            Friend friendship1 = friendRepository.findRelationshipByNodeIds(person.getId(), friend.getId());
            Friend friendship2 = friendRepository.findRelationshipByNodeIds(friend.getId(), person.getId());
            if (friendship1 != null) {
                friendRepository.delete(friendship1);
            }
            if (friendship2 != null) {
                friendRepository.delete(friendship2);
            }

            response.setSuccess(true);
            System.out.println(friendRepository.findAll());

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }


        return response;
    }

    @Override
    public GetFriendsResponse getFriends(GetFriendsRequest request) {
        GetFriendsResponse response = new GetFriendsResponse();

        try {
            Person person = personRepository.findByUserId(request.getUserId());
            if (person == null) {
                throw new Exception("user not found");
            }
            List<Person> friends = friendRepository.getFriendByUserId(person.getId());
            System.out.println(friends);
            response.setFriends(friends);
            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return response;
    }
}
