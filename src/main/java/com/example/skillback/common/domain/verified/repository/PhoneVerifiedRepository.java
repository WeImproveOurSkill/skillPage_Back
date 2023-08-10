package com.example.skillback.common.domain.verified.repository;

import com.example.skillback.common.domain.verified.entity.PhoneVerified;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneVerifiedRepository extends JpaRepository<PhoneVerified, Long> {

    PhoneVerified findByPhoneNumber(String phoneNumber);

}
