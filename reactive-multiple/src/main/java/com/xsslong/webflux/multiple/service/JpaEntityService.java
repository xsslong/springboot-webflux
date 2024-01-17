package com.xsslong.webflux.multiple.service;

import com.xsslong.webflux.multiple.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author xutong
 */
public interface JpaEntityService {

    User addUser(User dto);


    User delUser(User dto);


    Flux<User> selectAllUser();


    Mono<User> selectOne(final Long userId);
}
