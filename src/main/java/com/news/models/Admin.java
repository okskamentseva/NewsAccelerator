package com.news.models;


import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Admin {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }
}
