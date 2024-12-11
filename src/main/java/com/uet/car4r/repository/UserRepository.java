package com.uet.car4r.repository;

import com.uet.car4r.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, String> {
  boolean existsUsersByEmail(String email);

  User getAllByEmail(String email);

  String getPasswordByEmail(String email);

  @Modifying
  @Transactional
  @Query(value = "update User u set u.password = :password where u.email = :email")
  int updatePasswordByEmail(String email, String password);

  void deleteAllByEmail(String email);

  User getUserById(String id);

  User getUserByEmail(String email);
}
