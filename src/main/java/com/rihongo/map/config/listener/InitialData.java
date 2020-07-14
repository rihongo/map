package com.rihongo.map.config.listener;

import com.rihongo.map.model.entities.User;
import com.rihongo.map.model.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Profile({"local", "test"})
@Component
@Slf4j
@AllArgsConstructor
public class InitialData implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Started after Spring boot application !");
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .userId("tester")
                .password(passwordEncoder.encode("password"))
                .build());

        users.add(User.builder()
                .userId("test1234")
                .password(passwordEncoder.encode("test1234"))
                .build());

        userRepository.saveAll(users);
    }
}
