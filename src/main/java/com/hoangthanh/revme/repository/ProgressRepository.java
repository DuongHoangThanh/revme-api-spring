package com.hoangthanh.revme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangthanh.revme.models.Progress;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {

}
