package com.example.infosystem.repos;

import com.example.infosystem.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepo extends JpaRepository<Style, Long> {
}
