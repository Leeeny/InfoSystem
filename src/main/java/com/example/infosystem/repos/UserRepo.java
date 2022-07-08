package com.example.infosystem.repos;

import com.example.infosystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    public Optional<User> findUserByUserId(Long userId);

    public Optional<User> findUserByLogin(String login);

    boolean existsByLogin(String login);
}
