package com.example.qr_based_attendance_system.controller;

import com.example.qr_based_attendance_system.model.ClassAttendanceToken;
import com.example.qr_based_attendance_system.service.TokenService;
import com.example.qr_based_attendance_system.util.QrUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@RestController
public class ClassController {
    @Autowired
    TokenService tokenService;
    @Autowired
    QrUtil qrUtil;

    @GetMapping("/class/{classId}/qr")
    public void generateQr(@PathVariable String classId, HttpServletResponse response) throws Exception {

        ClassAttendanceToken token = tokenService.createToken(classId); // create token

        String url = "http://localhost:8080/attend?token=" + token.getToken(); // link for browser

        BufferedImage img = qrUtil.generateQr(url); // QR image

        response.setContentType("image/png"); // set type
        ImageIO.write(img, "PNG", response.getOutputStream()); // write to output
    }
}
