package com.example.qr_based_attendance_system.controller;

import com.example.qr_based_attendance_system.model.AttendanceRecord;
import com.example.qr_based_attendance_system.storage.InMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AttendanceAdminController {
    @Autowired
    InMemoryStore store;

    @GetMapping("/attendance/{classId}")
    public List<AttendanceRecord> getAttendance(@PathVariable String classId) {
        return store.getAttendanceRecords().stream()
                .filter(r -> r.getClassId().equals(classId)) // filter by class
                .collect(Collectors.toList()); // return list
    }
}
