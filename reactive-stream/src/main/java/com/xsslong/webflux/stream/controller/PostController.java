package com.xsslong.webflux.stream.controller;

import com.xsslong.webflux.stream.model.OnlineAmount;
import com.xsslong.webflux.stream.util.PostGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author HelloWood
 * @date 2019-01-08 15:22
 */
@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostGenerator postGenerator;

    @GetMapping(value = "/online/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<OnlineAmount> online() {
        return postGenerator.fetchPostStream(Duration.ofMillis(500)).share();
    }
}