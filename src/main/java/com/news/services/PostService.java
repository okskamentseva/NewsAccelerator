package com.news.services;

import com.news.DAO.AdminDAO;
import com.news.DAO.PostDAO;
import com.news.models.Post;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PostService {
    private final PostDAO postDAO;
    private final AdminDAO adminDAO;

    @Autowired
    public PostService(PostDAO postDAO, AdminDAO adminDAO) {
        this.postDAO = postDAO;
        this.adminDAO = adminDAO;
    }

    public Post add(String text, Long idAuthor) {
        try {
            Post post = new Post();
            post.setText(text);
            post.setAuthor(idAuthor);
            post.setLikes(0);

            postDAO.save(post);

            return post;
        } catch (Exception e) {
            return nullPost();
        }
    }

    public Optional<Post> get(Long id) {
        return postDAO.findById(id);
    }

    public Iterable<Post> getAllByAuthor(Long idAuthor) {
        return postDAO.findAllByAuthor(idAuthor);
    }

    public Post addLike(Long id) {
        Optional<Post> optionalPost = postDAO.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            int likes = post.getLikes() + 1;
            post.setLikes(likes);

            postDAO.save(post);

            return post;
        } else {
            return nullPost();
        }
    }

    public Post removeLike(Long id) {
        Optional<Post> optionalPost = postDAO.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            int likes = post.getLikes() - 1;
            post.setLikes(likes);

            postDAO.save(post);

            return post;
        } else {
            return nullPost();
        }
    }

    public Post rewrite(String text, Long id, Long idAuthor) {
        Optional<Post> optionalPost = postDAO.findById(id);

        if (optionalPost.isPresent() &&
                (adminDAO.findById(idAuthor).isPresent() || Objects.equals(optionalPost.get().getAuthor(), idAuthor))) {
            Post post = optionalPost.get();
            post.setText(text);

            postDAO.save(post);

            return post;
        } else {
            return nullPost();
        }
    }

    public Optional<Post> delete(Long id) {
        Optional<Post> optionalPost = postDAO.findById(id);

        optionalPost.ifPresent(postDAO::delete);

        return optionalPost;
    }

    private @NotNull Post nullPost() {
        Post post = new Post();
        post.setText(null);
        post.setLikes(-1);
        post.setAuthor(-1L);

        return post;
    }
}
