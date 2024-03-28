package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.EmployeeShadow;

public interface EmployeeShadowRepository extends JpaRepository<EmployeeShadow, Long>{

}
