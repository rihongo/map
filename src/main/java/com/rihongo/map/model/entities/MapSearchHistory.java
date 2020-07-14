package com.rihongo.map.model.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MapSearchHistory {

    @Id
    private String keyword;

    @Builder.Default
    private long count = 1;

    public MapSearchHistory setCount() {
        this.count++;
        return this;
    }
}
