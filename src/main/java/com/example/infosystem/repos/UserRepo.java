package com.example.infosystem.repos;

import com.example.infosystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    public User findUserByUserId(Long userId);
}
