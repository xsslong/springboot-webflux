package com.xsslong.weflux.multiple.entity;

import jakarta.persistence.*;

/**
 * @author xutong
 */
@Entity
@Table(name = "t_user")
public final class UserEntity extends User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public long getUserId() {
        return super.getUserId();
    }

    @Column(name = "name")
    public String getName() {
        return super.getName();
    }
}
