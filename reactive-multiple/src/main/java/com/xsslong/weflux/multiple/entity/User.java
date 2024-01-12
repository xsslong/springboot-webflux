package com.xsslong.weflux.multiple.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xutong
 */
@Setter
@Getter
public class User implements Serializable {

    private long userId;

    private String name;


    @Override
    public String toString() {
        return String.format(" user_id: %s, name: %s", userId, name);
    }
}
