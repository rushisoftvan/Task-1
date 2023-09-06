package com.softvan.repository;

import com.softvan.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository  extends JpaRepository<PatientEntity,Integer> {
}
