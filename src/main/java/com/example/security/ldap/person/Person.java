package com.example.security.ldap.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Entry(
    objectClasses = {"person", "top", "organizationalPerson", "inetOrgPerson"},
    base = "ou=people,dc=novatec,dc=de")
public class Person {

  @JsonIgnore @Id private Name dn;

  @Attribute(name = "cn")
  private String fullName;

  @Attribute(name = "uid")
  private String userName;

  @Attribute(name = "sn")
  private String lastName;

  @Attribute(name = "givenName")
  private String firstName;

  @JsonIgnore
  @Attribute(name = "userPassword")
  private String password;

  @DnAttribute(value = "ou")
  private String group;

  public Name getDn() {
    return dn;
  }

  public void setDn(Name dn) {
    this.dn = dn;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "Person{"
        + "dn="
        + dn
        + ", fullName='"
        + fullName
        + '\''
        + ", userName='"
        + userName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", firstName='"
        + firstName
        + '\''
        + ", password=[PROTECTED]"
        + '}';
  }
}
