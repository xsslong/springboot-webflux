package com.xsslong.webflux.redis.repository;

import com.xsslong.webflux.redis.model.Post;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

/**
 * @author HelloWood
 * @date 2019-01-08 14:22
 */
public interface PostRepository extends KeyValueRepository<Post, String> {

}
