package com.graphql.controller;

import com.graphql.dao.PostDao;
import com.graphql.domain.Author;
import com.graphql.domain.Post;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController {

    private final PostDao postDao;

    public AuthorController(PostDao postDao) {
        this.postDao = postDao;
    }

    @SchemaMapping
    public List<Post> posts(String authorId) {
        return postDao.getAuthorPosts(authorId);
    }
}
