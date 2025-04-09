package com.application.Project_Version_03_Spring_Boot.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.application.Project_Version_03_Spring_Boot.entity.BillEntity;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {

}