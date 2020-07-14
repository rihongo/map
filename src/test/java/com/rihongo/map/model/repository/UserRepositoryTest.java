package com.rihongo.map.model.repository;

import com.rihongo.map.model.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRegisterTest() {
        String userId = "test1234";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = User.builder()
                .userId(userId)
                .password(passwordEncoder.encode("test1234"))
                .build();

        testEntityManager.persist(user);

        assertThat(userRepository.findByUserId(userId).orElse(new User()), is(user));
    }
}
