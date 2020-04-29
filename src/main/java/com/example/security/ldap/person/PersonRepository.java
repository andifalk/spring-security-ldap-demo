package com.example.security.ldap.person;

import org.springframework.data.ldap.repository.LdapRepository;

import java.util.Optional;

public interface PersonRepository extends LdapRepository<Person> {

  Optional<Person> findOneByUserName(String username);
}
