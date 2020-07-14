package com.rihongo.map.model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


import java.io.Serializable;

@Getter
@Setter
@Builder
@RedisHash("user")
@NoArgsConstructor
@AllArgsConstructor
public class UserSession implements Serializable {

    @Id
    private long userNo;

    private String userId;
}
