package com.learning.example.spring.repository;
import com.learning.example.spring.entity.LogEvent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEventRepository extends JpaRepository<LogEvent, String> {
}
