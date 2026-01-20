package com.example.qr_based_attendance_system.service;

import com.example.qr_based_attendance_system.model.ClassAttendanceToken;
import com.example.qr_based_attendance_system.storage.InMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    @Autowired
    InMemoryStore store;

    public ClassAttendanceToken createToken(String classId) {
        String token = UUID.randomUUID().toString(); // unique id
        long expiresAt = System.currentTimeMillis() + (10 * 60 * 1000); // 10 min expiry

        ClassAttendanceToken obj = new ClassAttendanceToken();
        obj.setToken(token);
        obj.setClassId(classId);
        obj.setExpiresAt(expiresAt);

        store.getTokenStore().put(token, obj); // store token
        return obj;
    }

    public ClassAttendanceToken getToken(String token) {
        return store.getTokenStore().get(token); // fetch token
    }
}
