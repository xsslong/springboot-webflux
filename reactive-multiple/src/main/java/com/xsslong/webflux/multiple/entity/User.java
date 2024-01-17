package com.xsslong.webflux.multiple.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xutong
 */
@Setter
@Getter
public class User implements Serializable {

    private long id;

    private long userId;

    private String name;


    @Override
    public String toString() {
        return String.format(" id: %s,  user_id: %s, name: %s", id, userId, name);
    }
}
