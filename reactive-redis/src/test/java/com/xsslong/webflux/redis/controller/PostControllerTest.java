package com.xsslong.webflux.redis.controller;

import io.github.helloworlde.redis.RedisApplicationTests;
import com.xsslong.webflux.redis.model.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.stream.Collectors;

public class PostControllerTest extends RedisApplicationTests {

    @Autowired
    private ApplicationContext context;

    private WebTestClient client;

    @Before
    public void setUp() throws Exception {
        String baseUrl = "http://localhost:8080";
        client = WebTestClient
                .bindToApplicationContext(context)
                .configureClient()
                .baseUrl(baseUrl)
                .build();
    }

    @Test
    public void list() {
        FluxExchangeResult<Post> result = client.get()
                .uri("/posts")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Post.class);

        Assert.assertNotNull("Result body should not be null", result);
        Assert.assertNotNull("Result body content should not be null", result.getResponseBody());

        List<Post> postList = result.getResponseBody().toStream().collect(Collectors.toList());
        Assert.assertNotNull("The result body list should not be null", postList);
    }
}