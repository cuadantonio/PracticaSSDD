package com.example.demo.controller;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Cultivo;
import com.example.demo.model.Producto;
import com.example.demo.model.Tratamiento;
import com.example.demo.repository.CultivoRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.TratamientoRepository;
@Controller
public class AppController {
	@Autowired
	private CultivoRepository repCultivos;
	@Autowired
	private ProductoRepository repProductos;
	@Autowired
	private TratamientoRepository repTratamientos;
	public LinkedList<Tratamiento> listat = new LinkedList<>();
	LocalDate date = LocalDate.of(2000, 10, 10);
	@PostConstruct
	public void init() {
		Producto p = new Producto("producto","producto",10,10);
		Cultivo c = new Cultivo("especie","variedad",date,"zona",listat);
		Tratamiento t = new Tratamiento(c,p,"2",date,date,date);
		repCultivos.save(c);
		repProductos.save(p);
		repTratamientos.save(t);
		c.getTratamientos().add(t);
		repCultivos.save(c);
		
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
	@RequestMapping("/cultivos")
	public String cultivos(Model model) {
		List<Cultivo> listac = repCultivos.findAll();
		model.addAttribute("cultivos",listac);
		return "Cultivos";
	}
	@RequestMapping("/productos")
	public String productos(Model model) {
		List<Producto> listap = repProductos.findAll();
		model.addAttribute("productos",listap);
		return "productos";
	}
	@RequestMapping("/tratamientos")
	public String tratamientos(Model model) {
		List<Tratamiento> listataux = repTratamientos.findAll();
		model.addAttribute("tratamientos", listataux);
		return "Tratamientos";
	}
	@RequestMapping("/cultivos/{id}")
	public String mainCultivo(@PathVariable long id, Model model) {
		//Optional<Cultivo> cultivo = repCultivos.findById(id);
		//if(cultivo.isPresent()) {
			//model.addAttribute("cultivo",cultivo.get());
			//return "maincultivo";
		//}
		List<Cultivo> listac = repCultivos.findAll();
		model.addAttribute("cultivos",listac);
		return "maincultivo";
	}
}
