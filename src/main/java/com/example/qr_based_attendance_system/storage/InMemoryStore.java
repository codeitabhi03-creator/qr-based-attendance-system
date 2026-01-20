package com.example.qr_based_attendance_system.storage;

import com.example.qr_based_attendance_system.model.AttendanceRecord;
import com.example.qr_based_attendance_system.model.ClassAttendanceToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryStore {
    private final Map<String, ClassAttendanceToken> tokenStore = new ConcurrentHashMap<>(); // token store
    private final List<AttendanceRecord> attendanceRecords = Collections.synchronizedList(new ArrayList<>()); // attendance list

    public Map<String, ClassAttendanceToken> getTokenStore() { return tokenStore; }
    public List<AttendanceRecord> getAttendanceRecords() { return attendanceRecords; }
}
