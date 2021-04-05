package com.example.demo.controller;

import java.util.LinkedList;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Cultivo;
import com.example.demo.model.Producto;
@Controller
public class AppController {
	public LinkedList<Producto> lista = new LinkedList<>();
	@PostConstruct
	public void init() {
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
	@RequestMapping("/cultivos")
	public String cultivos(Model model) {
		return "Cultivos";
	}
	@RequestMapping("/productos")
	public String productos(Model model) {
		return "productos";
	}
	@RequestMapping("/tratamientos")
	public String tratamientos(Model model) {
		return "Tratamientos";
	}
}
