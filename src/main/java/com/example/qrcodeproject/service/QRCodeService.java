package com.example.qrcodeproject.service;

import com.example.qrcodeproject.entity.QRCodeEntity;
import com.example.qrcodeproject.repository.QRCodeRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class QRCodeService {

    private final QRCodeRepository qrCodeRepository;

    @Autowired
    public QRCodeService(QRCodeRepository qrCodeRepository) {
        this.qrCodeRepository = qrCodeRepository;
    }

    public String generateQrCodeAndSave(String link){
        try {
            int width = 300;
            int height = 300;
            String format = "png";

            // Генерация QR-кода
            Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(link, BarcodeFormat.QR_CODE, width, height, hints);

            BufferedImage qrCodeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            qrCodeImage.createGraphics();

            Graphics2D graphics = (Graphics2D) qrCodeImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (bitMatrix.get(x, y)) {
                        graphics.fillRect(x, y, 1, 1);
                    }
                }
            }

            // Сохранение QR-кода в байтовом массиве
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(qrCodeImage, format, byteArrayOutputStream);
            byte[] qrCodeBytes = byteArrayOutputStream.toByteArray();

            // Сохранение QR-кода в базе данных
            QRCodeEntity qrCodeEntity = new QRCodeEntity();
            qrCodeEntity.setLink(link);
            qrCodeEntity.setQrCodeImage(qrCodeBytes);
            qrCodeRepository.save(qrCodeEntity);

            return "successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed.";
        }

    }

    public QRCodeEntity getImage(int imageId) {
        Optional<QRCodeEntity> imageEntity = qrCodeRepository.findById(imageId);

        if (imageEntity.isPresent()) {
            QRCodeEntity image = imageEntity.get();
            return image;
        } else {
            return null;
        }
    }
}
