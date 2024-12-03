package com.uet.car4r.repository;

import com.uet.car4r.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {
  public boolean existsUsersByEmail(String email);
}
