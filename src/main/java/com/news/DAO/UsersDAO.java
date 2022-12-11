package com.news.DAO;

import com.news.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDAO extends CrudRepository<Users, Long> {
}
