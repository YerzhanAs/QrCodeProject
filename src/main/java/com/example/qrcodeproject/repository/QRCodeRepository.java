package com.example.qrcodeproject.repository;

import com.example.qrcodeproject.entity.QRCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCodeEntity, Integer> {
}
