package com.uet.car4r.repository;

import com.uet.car4r.constant.Role;
import com.uet.car4r.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> getStaffs(@Param("role") Role role);

    @Query("SELECT u FROM User u WHERE u.id = :id AND u.role = :role")
    Optional<User> getStaffById(@Param("id") String id, @Param("role") Role role);

    @Query("SELECT COUNT(u.id) FROM User u WHERE u.role = com.uet.car4r.constant.Role.CUSTOMER")
    long getNumberOfCustomer();

    boolean existsByUsername(String uniqueUsername);
}
