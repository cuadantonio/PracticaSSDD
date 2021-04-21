package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Cultivo;

public interface CultivoRepository extends JpaRepository<Cultivo, Long> {
	Cultivo findByespecie(String especie);
	List <Cultivo> findByOrderByEspecie();
}
