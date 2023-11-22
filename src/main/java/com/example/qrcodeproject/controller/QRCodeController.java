package com.example.qrcodeproject.controller;

import com.example.qrcodeproject.entity.QRCodeEntity;
import com.example.qrcodeproject.pojo.QRCodeRequest;
import com.example.qrcodeproject.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qrcodes")
public class QRCodeController {


    private QRCodeService qrCodeService;

    @Autowired
    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateQRCode(@RequestParam("link") String link) {
        String answer = qrCodeService.generateQrCodeAndSave(link);

        if ("successfully".equals(answer)) {
            return ResponseEntity.ok("QR code saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save QR code");
        }
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateQRCodeWithBody(@RequestBody QRCodeRequest qrCodeRequest){
        String answer = qrCodeService.generateQrCodeAndSave(qrCodeRequest.getLink());

        if ("successfully".equals(answer)) {
            return ResponseEntity.ok("QR code saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save QR code");
        }

    }

    @GetMapping("/{imageId}")
    public ResponseEntity<byte[]> getImage(@PathVariable int imageId) {
       QRCodeEntity imageEntity = qrCodeService.getImage(imageId);

        if (imageEntity!=null) {
            QRCodeEntity image = imageEntity;
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getLink() + "\"")
                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate media type (e.g., IMAGE_PNG, IMAGE_JPEG)
                    .body(image.getQrCodeImage());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
