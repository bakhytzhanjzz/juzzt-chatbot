package com.juzzt.chatbot.controller;

import com.juzzt.chatbot.model.UserQueryLog;
import com.juzzt.chatbot.service.UserQueryLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/query-logs")
public class UserQueryLogController {

    private final UserQueryLogService userQueryLogService;

    @Autowired
    public UserQueryLogController(UserQueryLogService userQueryLogService) {
        this.userQueryLogService = userQueryLogService;
    }

    @GetMapping
    public ResponseEntity<List<UserQueryLog>> getAllLogs() {
        List<UserQueryLog> logs = userQueryLogService.getAllLogs();
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserQueryLog> getLogById(@PathVariable Long id) {
        Optional<UserQueryLog> log = userQueryLogService.getLogById(id);
        return log.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserQueryLog>> searchLogsByQuery(@RequestParam String query) {
        List<UserQueryLog> logs = userQueryLogService.searchLogsByQuery(query);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/timestamp")
    public ResponseEntity<List<UserQueryLog>> getLogsByTimestampAfter(@RequestParam String timestamp) {
        List<UserQueryLog> logs = userQueryLogService.getLogsByTimestampAfter(timestamp);
        return ResponseEntity.ok(logs);
    }

    @PostMapping
    public ResponseEntity<UserQueryLog> createLog(@RequestBody UserQueryLog log) {
        UserQueryLog createdLog = userQueryLogService.createLog(log);
        return ResponseEntity.status(201).body(createdLog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
        userQueryLogService.deleteLog(id);
        return ResponseEntity.noContent().build();
    }
}
