package com.example.qr_based_attendance_system.controller;

import com.example.qr_based_attendance_system.model.AttendanceRecord;
import com.example.qr_based_attendance_system.model.ClassAttendanceToken;
import com.example.qr_based_attendance_system.service.TokenService;
import com.example.qr_based_attendance_system.storage.InMemoryStore;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {
    @Autowired
    TokenService tokenService;

    @Autowired
    InMemoryStore store;

    @GetMapping("/attend")
    public String attend(@RequestParam String token, HttpServletRequest request) {
        String ip = request.getRemoteAddr(); // client ip

        ClassAttendanceToken t = tokenService.getToken(token); // fetch token
        if (t == null) return "Invalid QR";

        long now = System.currentTimeMillis(); // current time
        if (now > t.getExpiresAt()) return "QR expired";

        if (t.getUsedIps().contains(ip)) return "Already used from this device";

        // ask student to input details (simple HTML)
        return "<form action='/attend/submit' method='post'>" +
                "<input type='hidden' name='token' value='"+token+"'/>" +
                "Name: <input name='name'/><br/>" +
                "Roll No: <input name='roll'/><br/>" +
                "<button type='submit'>Submit</button>" +
                "</form>";
    }

    @PostMapping("/attend/submit")
    public String submit(@RequestParam String token,
                         @RequestParam String name,
                         @RequestParam String roll,
                         HttpServletRequest request) {

        String ip = request.getRemoteAddr(); // client ip
        ClassAttendanceToken t = tokenService.getToken(token); // fetch token

        if (t == null) return "Invalid token";
        if (t.getUsedIps().contains(ip)) return "Already used from this device";

        t.getUsedIps().add(ip); // mark as used

        AttendanceRecord record = new AttendanceRecord();
        record.setClassId(t.getClassId());
        record.setStudentName(name);
        record.setRollNo(roll);
        record.setIp(ip);
        record.setTimestamp(System.currentTimeMillis());

        store.getAttendanceRecords().add(record); // save record

        return "Attendance Marked Successfully";
    }
}
