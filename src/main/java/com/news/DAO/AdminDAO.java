package com.news.DAO;

import com.news.models.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDAO extends CrudRepository<Admin, Long> {
}
