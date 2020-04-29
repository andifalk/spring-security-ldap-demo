package com.example.security.ldap;

import com.example.security.ldap.person.Person;
import com.example.security.ldap.person.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class DemoRestController {

  private static final Logger LOG = LoggerFactory.getLogger(DemoRestController.class);

  private final PersonService personService;

  public DemoRestController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping("/")
  public String demo(
      @AuthenticationPrincipal org.springframework.security.ldap.userdetails.Person person) {

    LOG.info("Successfully authenticated user {}", person);

    return "<html><body>"
        + "<h1>Spring Security LDAP Demo</h1>"
        + String.format(
            "<h2>Hello '%s', %s %s (%s %s)</h2>",
            person.getUsername(),
            person.getGivenName(),
            person.getSn(),
            person.getDn(),
            person.getAuthorities())
        + "<p><a href=\"/persons\">get a list of all persons from LDAP</a></p>";
  }

  @GetMapping("/persons")
  public List<Person> findAllPersons() {
    return StreamSupport.stream(personService.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @GetMapping("/persons/{username}")
  public ResponseEntity<Person> findPerson(@PathVariable("username") String username) {
    return personService
        .findOneByUserName(username)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
