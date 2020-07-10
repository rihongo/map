package com.rihongo.map.model;

import lombok.Builder;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


import java.io.Serializable;

@Getter
@Setter
@Builder
@RedisHash("user")
public class UserSession implements Serializable {

    @Id
    private long userNo;

    private String userId;

}
