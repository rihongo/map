package com.rihongo.map;

import com.rihongo.map.controller.MapController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@Import(MapController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MapApplicationTests {

    @Autowired
    private MapController mapController;

    @Test
    public void contextLoads() {
        assertThat(mapController, is(notNullValue()));
    }

}
