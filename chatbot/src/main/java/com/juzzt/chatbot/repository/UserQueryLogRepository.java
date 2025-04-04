package com.juzzt.chatbot.repository;

import com.juzzt.chatbot.model.UserQueryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserQueryLogRepository extends JpaRepository<UserQueryLog, Long> {

    List<UserQueryLog> findByTimestampAfter(LocalDateTime timestamp); // Find logs after a certain time

    List<UserQueryLog> findByUserQueryContainingIgnoreCase(String query); // Search logs by user query
}
