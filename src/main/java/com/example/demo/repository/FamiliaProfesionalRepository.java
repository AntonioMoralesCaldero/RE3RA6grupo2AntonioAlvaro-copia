package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FamiliaProfesional;

@Repository
public interface FamiliaProfesionalRepository extends JpaRepository<FamiliaProfesional, Integer> {
	FamiliaProfesional findById(FamiliaProfesional familiaProfesional);
}