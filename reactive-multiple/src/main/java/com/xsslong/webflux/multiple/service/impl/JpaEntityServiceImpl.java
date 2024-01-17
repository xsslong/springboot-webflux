package com.xsslong.webflux.multiple.service.impl;

import com.xsslong.webflux.multiple.repository.JpaUserRepository;
import com.xsslong.webflux.multiple.entity.User;
import com.xsslong.webflux.multiple.entity.UserEntity;
import com.xsslong.webflux.multiple.service.JpaEntityService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author xutong
 */
@Slf4j
@Service
public class JpaEntityServiceImpl implements JpaEntityService {

    @Resource
    public JpaUserRepository userRepository;

    //增加用户
    @Transactional
    @Override
    public User addUser(User dto) {
        User userEntity = new UserEntity();
        userEntity.setUserId(dto.getUserId());
        userEntity.setName(dto.getName());
        userRepository.save(userEntity);
        BeanUtils.copyProperties(userEntity, dto);
        return dto;
    }

    //删除用户
    @Transactional
    @Override
    public User delUser(User dto) {
        userRepository.deleteById(dto.getUserId());
        return dto;
    }

    //查询全部用户
    @Override
    public Flux<User> selectAllUser() {
        log.info("方法 selectAllUser 被调用了");
        return userRepository.findAll();
    }

    //查询一个用户
    @Override
    public Mono<User> selectOne(final Long userId) {
        log.info("方法 selectOne 被调用了");
        return userRepository.findById(userId);
    }
}
