package server.service.impl;

import api.req.*;
import api.res.*;
import core.Person;
import core.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
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

    @Resource
    PersonRepository personRepository;

    private final static Logger log = LoggerFactory.getLogger(SocialNetworkApplication.class);

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        System.out.println("createUser");

        personRepository.deleteAll();

        // Names should be unique
        Person greg = new Person("Greg");
        Person roy = new Person("Roy");
        Person craig = new Person("Craig");
        Person craigs = new Person("Craigss");

        List<Person> team = Arrays.asList(greg, roy, craig, craigs);

        log.info("Before linking up with Neo4j...");

        team.forEach(person -> log.info("\t" + person.toString()));

        personRepository.save(greg);
        personRepository.save(roy);
        personRepository.save(craig);
        personRepository.save(craigs);

        greg = personRepository.findByName(greg.getName());
        greg.addFriend(roy);
        greg.addFriend(craig);
        personRepository.save(greg);

        roy = personRepository.findByName(roy.getName());
        roy.addFriend(craig);
        //We already know that roy works with greg
        personRepository.save(roy);

        //We already know craig works with roy and greg

        log.info("Lookup each person by name...");
        team.forEach(person -> log.info("\t" + personRepository.findByName(person.getName()).toString()));

        List<Person> friends = personRepository.findByFriendsName(greg.getName());
        log.info("The following have Greg as a friend...");
        friends.forEach(person -> log.info("\t" + person.getName()));


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
