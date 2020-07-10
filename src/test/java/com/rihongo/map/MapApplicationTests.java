package com.rihongo.map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MapApplicationTests {

    @Value("${val}")
    private String val;

    @Test
    void contextLoads() {
        System.out.println(val);
    }

}
