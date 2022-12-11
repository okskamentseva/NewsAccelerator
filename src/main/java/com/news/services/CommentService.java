package com.news.services;

import com.news.DAO.AdminDAO;
import com.news.DAO.CommentDAO;
import com.news.models.Comment;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentDAO commentDAO;
    private final AdminDAO adminDAO;

    @Autowired
    public CommentService(CommentDAO commentDAO, AdminDAO adminDAO) {
        this.commentDAO = commentDAO;
        this.adminDAO = adminDAO;
    }

    public Iterable<Comment> getAllByIdPost(Long id_post) {
        return commentDAO.findAllByIdPost(id_post);
    }

    public Optional<Comment> get(Long id) {
        return commentDAO.findById(id);
    }

    public Iterable<Comment> getAllByAuthor(Long idAuthor) {
        return commentDAO.getAllByAuthor(idAuthor);
    }

    public Comment add(Long idPost, String commentText, Long idAuthor) {
        try {
            Comment comment = new Comment();
            comment.setAuthor(idAuthor);
            comment.setText(commentText);
            comment.setId_post(idPost);
            comment.setLikes(0);

            commentDAO.save(comment);

            return comment;
        } catch (Exception e) {
            return nullComment();
        }
    }

    public Comment addLike(Long id) {
        Optional<Comment> optionalComment = commentDAO.findById(id);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            int likes = comment.getLikes() + 1;
            comment.setLikes(likes);

            commentDAO.save(comment);

            return comment;
        } else {
            return nullComment();
        }
    }

    public Comment removeLike(Long id) {
        Optional<Comment> optionalComment = commentDAO.findById(id);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            int likes = comment.getLikes() - 1;
            comment.setLikes(likes);

            commentDAO.save(comment);

            return comment;
        } else {
            return nullComment();
        }
    }

    public Optional<Comment> delete(Long id) {
        Optional<Comment> optionalComment = commentDAO.findById(id);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            commentDAO.delete(comment);
        }

        return optionalComment;
    }

    public Comment rewrite(String text, Long id, Long idAuthor) {
        Optional<Comment> optionalComment = commentDAO.findById(id);

        if (optionalComment.isPresent() &&
                (adminDAO.findById(idAuthor).isPresent() || Objects.equals(optionalComment.get().getAuthor(), idAuthor))) {
            Comment comment = optionalComment.get();
            comment.setText(text);

            commentDAO.save(comment);

            return comment;
        } else {
            return nullComment();
        }
    }

    public boolean deleteAll(Long idPost) {
        try (Connection connection = get();
             Statement statement = connection.createStatement()){
            int rows = statement.executeUpdate("DELETE FROM comment WHERE id_post = " + idPost);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection get() throws SQLException {
        String address, login, password;
        address = login = password = null;

        try(BufferedReader bufferedReader = new BufferedReader(
                new FileReader("./src/main/resources/application.properties")
        )) {
            int index = 0;
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                index++;

                if (index == 3) {
                    address = line.split("=")[1];
                }
                if (index == 4) {
                    login = line.split("=")[1];
                }
                if (index == 5) {
                    password = line.split("=")[1];
                }
                if (index == 6) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert address != null;
        return DriverManager.getConnection(address, login, password);
    }
    private @NotNull Comment nullComment() {
        Comment comment = new Comment();

        comment.setAuthor(-1L);
        comment.setText(null);
        comment.setId_post(-1L);
        comment.setLikes(-1);

        return comment;
    }
}