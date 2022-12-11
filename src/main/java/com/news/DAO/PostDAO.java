package com.news.DAO;

import com.news.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDAO extends CrudRepository<Post, Long> {
    Iterable<Post> findAllByAuthor(Long idAuthor);
}
