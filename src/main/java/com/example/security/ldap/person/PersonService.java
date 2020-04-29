package com.example.security.ldap.person;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

  private final PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public Iterable<Person> findAll() {
    return personRepository.findAll();
  }

  public Optional<Person> findOneByUserName(String username) {
    return personRepository.findOneByUserName(username);
  }
}
