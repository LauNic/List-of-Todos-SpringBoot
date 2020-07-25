package com.niculescu.rest.webservices.restfulwebservice.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
    inMemoryUserList.add(new JwtUserDetails(1L, "lau",
            "$2a$10$URj9hkvyt1KYuooPvvKZZu/miqvXmUn1zeAOjLT2MMyL0YfdO.N12", "ROLE_USER_1"));
    inMemoryUserList.add(new JwtUserDetails(2L, "teamlead",
            "$2a$10$6OzdhEDKloDKiVK3D91No.9pthHaur2vtS1CzGceZYzfJ1eNKw/5y", "ROLE_USER_2"));
    inMemoryUserList.add(new JwtUserDetails(3L, "analyst",
            "$2a$10$Pqj/wjEKMrRDeBbs8Kejq.6ZV3A.CS/f1/HK/kxj2c7PkdVL5W/hq", "ROLE_USER_3"));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}


