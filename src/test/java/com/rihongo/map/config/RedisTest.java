package com.rihongo.map.config;

import com.rihongo.map.model.UserSession;
import com.rihongo.map.model.repository.UserRedisRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
@Import(EmbeddedRedisConfig.class)
@DataRedisTest
public class RedisTest {

    @Autowired
    private UserRedisRepository userRedisRepository;

    @After
    public void tearDown() throws Exception {
        userRedisRepository.deleteAll();
    }

    @Test
    public void userRegisterTest() {
        String userId = "tester";
        long userNo = 1;

        UserSession user = UserSession.builder()
                .userNo(userNo)
                .userId(userId)
                .build();


        userRedisRepository.save(user);

        UserSession savedUser = userRedisRepository.findById(userNo).orElseThrow();

        assertThat(savedUser.getUserId(), equalTo(userId));
    }
}
