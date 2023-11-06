package server.service.impl;

import api.req.*;
import api.res.*;
import common.Constants;
import common.MD5Util;
import common.RedisUtil;
import core.Friend;
import core.FriendRepository;
import core.Person;
import core.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.service.SocialNetworkService;
import web.SocialNetworkApplication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yunjia
 */
@Service
public class SocialNetworkImpl implements SocialNetworkService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    private RedisUtil redisUtil;

    private final static Logger log = LoggerFactory.getLogger(SocialNetworkApplication.class);

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {

        System.out.println("createUser");
        //personRepository.deleteAll();

        CreateUserResponse response = new CreateUserResponse();

        // Names should be unique
        try {
            if (personRepository.findByName(request.getName()) != null) {
                throw new Exception("user already exists");
            }

            if(!request.getRole().equals("admin") && !request.getRole().equals("user")){
                throw new Exception("role should be admin or user");
            }

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encryptedPassword = passwordEncoder.encode(request.getPassword());
            //String encryptedPassword = MD5Util.encrypt(request.getPassword());
            Person p = Person.builder().encryptedPassword(encryptedPassword).name(request.getName())
                    .role(request.getRole()).build();
            personRepository.save(p);
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
            Person person = personRepository.findByName(request.getName());
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
            Person person = personRepository.findByName(request.getUserName());
            if (person == null) {
                throw new Exception("user not found");
            }
            Person friend = personRepository.findByName(request.getFriendName());
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

            Person person = personRepository.findByName(request.getUserName());
            if (person == null) {
                throw new Exception("user not found");
            }
            Person friend = personRepository.findByName(request.getFriendName());
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
            Person person = personRepository.findByName(request.getName());
            if (person == null) {
                throw new Exception("user not found");
            }
            List<Person> friends = friendRepository.getFriendByUserId(person.getId());
            //remove password
            for (Person friend : friends) {
                friend.setEncryptedPassword("");
            }

            System.out.println(friends);
            response.setFriends(friends);
            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public AddCreditResponse addCredit(AddCreditRequest request) {
        AddCreditResponse response = new AddCreditResponse();

        try {
            Person person = personRepository.findByName(request.getName());
            if (person == null) {
                throw new Exception("user not found");
            }
            person.setCredit(person.getCredit() + request.getCredit());
            personRepository.save(person);

            String val = person.getName();
            String key = "credit";

            redisUtil.zRemove(key, val);

            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public GetCreditResponse getCredit(GetCreditRequest request) {
        GetCreditResponse response = new GetCreditResponse();
        try {
            // check the cache first
            String key = "credit";
            String val = request.getName();
            Double credit = redisUtil.zScore(key, val);
            if (credit != null) {

                response.setCredit(Double.valueOf(credit).longValue());
                response.setSuccess(true);
                return response;
            }else {
                // if not in cache, get from db
                Person person = personRepository.findByName(request.getName());
                if (person == null) {
                    throw new Exception("user not found");
                }
                response.setCredit(person.getCredit());
                response.setSuccess(true);
                // update redis using zset, adding expire time
                redisUtil.zAdd(key, val, person.getCredit());
                redisUtil.expire(key, Constants.REDIS_EXPIRE_TIME, TimeUnit.SECONDS);
            }

        }
        catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;

    }
}
