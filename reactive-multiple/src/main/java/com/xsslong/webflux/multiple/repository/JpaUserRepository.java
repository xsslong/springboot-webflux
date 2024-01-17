package com.xsslong.webflux.multiple.repository;

import com.xsslong.webflux.multiple.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author xutong
 */
public interface JpaUserRepository extends ReactiveCrudRepository<User, Long> {

}
