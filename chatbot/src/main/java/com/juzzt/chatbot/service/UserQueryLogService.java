package com.juzzt.chatbot.service;

import com.juzzt.chatbot.model.UserQueryLog;
import com.juzzt.chatbot.repository.UserQueryLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class UserQueryLogService {

    private final UserQueryLogRepository userQueryLogRepository;

    @Autowired
    public UserQueryLogService(UserQueryLogRepository userQueryLogRepository) {
        this.userQueryLogRepository = userQueryLogRepository;
    }

    public List<UserQueryLog> getAllLogs() {
        return userQueryLogRepository.findAll();
    }

    public Optional<UserQueryLog> getLogById(Long id) {
        return userQueryLogRepository.findById(id);
    }

    public List<UserQueryLog> getLogsByTimestampAfter(String timestamp) {
        // Parse the string timestamp into LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime parsedTimestamp = LocalDateTime.parse(timestamp, formatter);

        return userQueryLogRepository.findByTimestampAfter(parsedTimestamp);
    }

    public List<UserQueryLog> searchLogsByQuery(String query) {
        return userQueryLogRepository.findByUserQueryContainingIgnoreCase(query);
    }

    public UserQueryLog createLog(UserQueryLog log) {
        return userQueryLogRepository.save(log);
    }

    public void deleteLog(Long id) {
        userQueryLogRepository.deleteById(id);
    }
}
