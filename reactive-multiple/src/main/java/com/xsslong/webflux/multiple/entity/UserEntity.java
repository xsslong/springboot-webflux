package com.xsslong.webflux.multiple.entity;

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
    public long getId() {
        return super.getId();
    }

    @Column(name = "user_id")
    @Override
    public long getUserId() {
        return super.getUserId();
    }

    @Column(name = "name")
    @Override
    public String getName() {
        return super.getName();
    }
}
