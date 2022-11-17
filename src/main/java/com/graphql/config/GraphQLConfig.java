package com.graphql.config;

import com.graphql.dao.AuthorDao;
import com.graphql.dao.PostDao;
import com.graphql.domain.Author;
import com.graphql.domain.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GraphQLConfig {

    @Bean
    public void initializeData() {
        for (int authorId = 0; authorId < 10; ++authorId) {
            Author author = new Author();
            author.setId("id" + authorId);
            author.setName("Author" + authorId);
            author.setThumbnail("http://example.com/author/" + authorId);
            authorDao().saveAuthor(author);
        }
        for (int postId = 0; postId < 100; ++postId) {
            Post post = new Post();
            Author author = authorDao().getAuthor("id" + postId / 10);
            post.setId("id" + postId);
            post.setTitle("Post:" + postId);
            post.setCategory("Post category");
            post.setText("Post " + postId + " by author " + author.getName());
            post.setAuthor(author);
            postDao().savePost(post);
        }

    }

    @Bean
    public PostDao postDao() {
        List<Post> posts = new ArrayList<>();

        return new PostDao(posts);
    }

    @Bean
    public AuthorDao authorDao() {
        List<Author> authors = new ArrayList<>();

        return new AuthorDao(authors);
    }
}
