package com.example.qr_based_attendance_system.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;

@Component
public class QrUtil {
    public BufferedImage generateQr(String text) throws Exception {
        QRCodeWriter writer = new QRCodeWriter(); // QR writer
        BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, 300, 300); // encode text
        return MatrixToImageWriter.toBufferedImage(matrix); // convert to image
    }
}
