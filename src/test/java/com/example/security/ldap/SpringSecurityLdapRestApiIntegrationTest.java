package com.example.security.ldap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SpringSecurityLdapRestApiIntegrationTest {

  private MockMvc mockMvc;

  @BeforeEach
  void init(WebApplicationContext webApplicationContext) {
    this.mockMvc =
        MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .defaultRequest(get("/").accept(MediaType.APPLICATION_JSON))
            .apply(springSecurity())
            .build();
  }

  @Test
  void authenticatedAccessSuccess() throws Exception {
    this.mockMvc
        .perform(get("/").with(httpBasic("bob", "bobpassword")))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  void wrongPasswordAccessFail() throws Exception {
    this.mockMvc
        .perform(get("/").with(httpBasic("bob", "invalid")))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void unauthenticatedAccessFail() throws Exception {
    this.mockMvc.perform(get("/")).andExpect(status().isUnauthorized());
  }

  @Test
  void getPersonsSuccess() throws Exception {
    this.mockMvc
        .perform(get("/persons").with(httpBasic("bob", "bobpassword")))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void getPersonSuccess() throws Exception {
    this.mockMvc
        .perform(get("/persons/alice").with(httpBasic("bob", "bobpassword")))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void getPersonNotFound() throws Exception {
    this.mockMvc
        .perform(get("/persons/unknown").with(httpBasic("bob", "bobpassword")))
        .andExpect(status().isNotFound());
  }
}
