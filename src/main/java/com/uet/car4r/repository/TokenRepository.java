package com.uet.car4r.repository;

import com.uet.car4r.constant.TokenType;
import com.uet.car4r.entity.Token;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {
  @Modifying
  @Transactional
  @Query("delete from Token token where token.user.id = :userId")
  int deleteAllByUserId(@Param("userId") String userId);

  Token findByToken(String token);

  void deleteAllByUserIdAndTokenType(String userId, TokenType tokenType);

  boolean existsTokenByToken(String token);
}
