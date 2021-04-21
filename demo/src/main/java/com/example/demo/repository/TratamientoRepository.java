package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Tratamiento;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
	Tratamiento findBynumeroLote(String numeroLote);
	List <Tratamiento> findByOrderByFechaPlazoReentrada();
	List <Tratamiento> findByOrderByFechaPlazoRecoleccion();
	List<Tratamiento> findByFechaPlazoReentradaOrFechaPlazoRecoleccionGreaterThan(LocalDate fechaReentrada, LocalDate fechaRecoleccion);
}
