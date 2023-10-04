package web;

import core.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication(scanBasePackages = {"api", "core", "server", "web","common"})
@EntityScan(basePackages = {"core"})
@EnableNeo4jRepositories(basePackages = {"core"})
//@EnableNeo4jRepositories
public class SocialNetworkApplication {

    //private final static Logger log = LoggerFactory.getLogger(SocialNetworkApplication.class);

    //@Autowired
    //private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkApplication.class, args);
    }

    //@Bean
    //CommandLineRunner demo(PersonRepository personRepository) {
    //    return args -> {
    //        personRepository.deleteAll();
    //
    //        // Names should be unique
    //        Person greg = new Person("Greg");
    //        Person roy = new Person("Roy");
    //        Person craig = new Person("Craig");
    //
    //        List<Person> team = Arrays.asList(greg, roy, craig);
    //
    //        log.info("Before linking up with Neo4j...");
    //
    //        team.forEach(person -> log.info("\t" + person.toString()));
    //
    //        personRepository.save(greg);
    //        personRepository.save(roy);
    //        personRepository.save(craig);
    //
    //        greg = personRepository.findByName(greg.getName());
    //        greg.addFriend(roy);
    //        greg.addFriend(craig);
    //        personRepository.save(greg);
    //
    //        roy = personRepository.findByName(roy.getName());
    //        roy.addFriend(craig);
    //        //We already know that roy works with greg
    //        personRepository.save(roy);
    //
    //        //We already know craig works with roy and greg
    //
    //        log.info("Lookup each person by name...");
    //        team.forEach(person -> log.info("\t" + personRepository.findByName(person.getName()).toString()));
    //
    //        List<Person> friends = personRepository.findByFriendsName(greg.getName());
    //        log.info("The following have Greg as a friend...");
    //        friends.forEach(person -> log.info("\t" + person.getName()));
    //    };
    //}
}