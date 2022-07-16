package com.example.infosystem.repos;

import com.example.infosystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findUserByUserId(Long userId);

    Optional<User> findUserByLogin(String login);

    boolean existsByLogin(String login);

    @Transactional
    @Modifying
    @Query("delete from User u where u.userId = ?1")
    void deleteUserByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("update User u set u.login=?2 where u.userId = ?1")
    void updateUserByUserId(Long userId, String username);
}
