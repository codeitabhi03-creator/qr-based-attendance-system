package com.example.qr_based_attendance_system.model;

import java.util.HashSet;
import java.util.Set;

public class ClassAttendanceToken {
    private String token;            // unique token for QR
    private String classId;          // class identifier
    private long expiresAt;          // epoch millis
    private Set<String> usedIps = new HashSet<>(); // tracks IP reuse

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Set<String> getUsedIps() {
        return usedIps;
    }

    public void setUsedIps(Set<String> usedIps) {
        this.usedIps = usedIps;
    }
}
