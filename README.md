# Spring Security LDAP Demo

This repository contains a simple demo for authenticating users with Spring Security and 
an embedded LDAP server.

The app provides the following Rest API endpoints:

* [Application Index](http://localhost:8080) 
* [List all persons from LDAP](http://localhost:8080/persons) 
* [Find a person in LDAP by user name (e.g. Bob)](http://localhost:8080/persons/bob) 

All endpoints require authentication, so you will see a login screen first.

To login use one of the following users:

| user id | password        |
|---------|-----------------|
| alice   | alicepassword   |
| bob     | bobpassword     |
| clara   | secret          |
| joe     | joepassword     |

The embedded LDAP server initializes its user data by reading the file _test-server.ldif_.
This file includes all users together with their encrypted passwords 
(using secure hashing encoders BCrypt, SCrypt and Argon2). One user (_clara_) uses an insecure clear password 
(just for demo purposes, don't do this in Production!). 

### Reference Documentation
For further reference, please consider the following sections:

* [Embedded LDAP Server](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/html/boot-features-nosql.html#boot-features-ldap-embedded)
* [Spring LDAP](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-ldap)

### Guides
The following guides illustrate how to use some features concretely:

* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)


