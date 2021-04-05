package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Cultivo;

public interface CultivoRepository extends JpaRepository<Cultivo, Long> {

}
