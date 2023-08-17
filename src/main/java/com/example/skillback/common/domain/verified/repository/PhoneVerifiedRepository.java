package com.example.skillback.common.domain.verified.repository;

import com.example.skillback.common.domain.verified.entity.PhoneVerified;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneVerifiedRepository extends CrudRepository<PhoneVerified, String> {

    PhoneVerified findByPhoneNumber(String phoneNumber);

}
