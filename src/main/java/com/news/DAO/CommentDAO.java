package com.news.DAO;

import com.news.models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO extends CrudRepository<Comment, Long> {
    Iterable<Comment> findAllByIdPost(Long id_post);
    boolean deleteAllByIdPost(Long id_post);
    Iterable<Comment> getAllByAuthor(Long idAuthor);
}
