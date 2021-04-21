package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		Producto p1 = new Producto("producto1","producto1",10,10);
		Producto p2 = new Producto("producto2","producto2",20,20);
		Producto p3 = new Producto("producto3","producto3",30,30);
		Producto p4 = new Producto("producto3","producto3",0,40);
		Producto p5 = new Producto("producto5","producto5",0,0);
		Cultivo c = new Cultivo("especie","variedad",date,"zona",listat);
		Tratamiento t = new Tratamiento(c,p1,"2",date,date,date);
		Tratamiento t2 = new Tratamiento(c,p1,"23",date,date,date);
		repCultivos.save(c);
		repProductos.save(p1);
		repProductos.save(p2);
		repProductos.save(p3);
		repProductos.save(p4);
		repProductos.save(p5);
		repTratamientos.save(t);
		repTratamientos.save(t2);
		c.getTratamientos().add(t);
		c.getTratamientos().add(t2);
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
		Optional<Cultivo> cultivo = repCultivos.findById(id);
		if(cultivo.isPresent()) {
			model.addAttribute("cultivo",cultivo.get());
			return "maincultivo";
		}
		return "cultivos";
	}
	@RequestMapping("/editarcultivo/{id}")
	public String editarCultivo(@PathVariable long id, Model model) {
		model.addAttribute("id",id);
		return "editarcultivo";
	}
	@RequestMapping("/cultivoeditado")
	public String cultivoEditado(@RequestParam String especie,@RequestParam String variedad,@RequestParam String fecha, @RequestParam String zona, @RequestParam Long id, Model model) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//convert String to LocalDate
		LocalDate localDate = LocalDate.parse(fecha, formatter);
		Optional<Cultivo> cultivo = repCultivos.findById(id);
		cultivo.get().setEspecie(especie);
		cultivo.get().setVariedad(variedad);
		cultivo.get().setFecha(localDate);
		cultivo.get().setZona(zona);
		repCultivos.save(cultivo.get());
		return "cultivoeditado";
	}
	@RequestMapping("/añadircultivo")
	public String añadirCultivo(Model model) {
		return "añadircultivo";
	}
	@RequestMapping("/cultivoañadido")
	public String cultivoAñadido(@RequestParam String especie,@RequestParam String variedad,@RequestParam String fecha, @RequestParam String zona, Model model) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//convert String to LocalDate
		LocalDate localDate = LocalDate.parse(fecha, formatter);
		LinkedList<Tratamiento> listat = new LinkedList<>();
		Cultivo cultivo = new Cultivo(especie,variedad,localDate,zona,listat);
		repCultivos.save(cultivo);
		return "cultivoañadido";
	}
	@RequestMapping("/tratamientos/{id}")
	public String mainTratamiento(@PathVariable long id, Model model) {
		Optional<Tratamiento> tratamiento = repTratamientos.findById(id);
		if(tratamiento.isPresent()) {
			model.addAttribute("tratamiento",tratamiento.get());
			model.addAttribute("producto",tratamiento.get().getProducto());
			model.addAttribute("cultivo",tratamiento.get().getCultivo());
			return "maintratamiento";
		}
		return "Tratamientos";
	}
	@RequestMapping("/añadirtratamiento")
	public String añadirTratamiento(Model model) {
		List<Cultivo> listac = repCultivos.findAll();
		List<Producto> listap = repProductos.findAll();
		model.addAttribute("cultivos",listac);
		model.addAttribute("productos",listap);
		return "añadirtratamiento";
	}
	@RequestMapping("/tratamientoañadido")
	public String tratamientoAñadido(@RequestParam String producto, @RequestParam String cultivo, @RequestParam String numeroLote,@RequestParam String fechaRealizacion, Model model) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//convert String to LocalDate
		LocalDate localDate = LocalDate.parse(fechaRealizacion, formatter);
		Producto paux = repProductos.findBynombre(producto);
		Cultivo caux = repCultivos.findByespecie(cultivo);
		Long reentrada = Long.valueOf(paux.getPlazoReentrada());
		Long recoleccion = Long.valueOf(paux.getPlazoRecoleccion());
		LocalDate fechaReentrada = localDate.plusDays(reentrada);
		LocalDate fechaRecoleccion = localDate.plusDays(recoleccion);
		Tratamiento trat = new Tratamiento(caux,paux,numeroLote,localDate,fechaReentrada,fechaRecoleccion);
		repTratamientos.save(trat);
		caux.getTratamientos().add(trat);
		repCultivos.save(caux);
		return "tratamientoañadido";
	}
	@RequestMapping("/editartratamiento/{id}")
	public String editarTratamiento(@PathVariable long id, Model model) {
		model.addAttribute("id",id);
		List<Cultivo> listac = repCultivos.findAll();
		List<Producto> listap = repProductos.findAll();
		model.addAttribute("cultivos",listac);
		model.addAttribute("productos",listap);
		return "editartratamiento";
	}
	@RequestMapping("/tratamientoeditado")
	public String tratamientoEditado(@RequestParam String producto, @RequestParam String cultivo, @RequestParam String numeroLote,@RequestParam String fechaRealizacion, @RequestParam Long id, Model model) {
		Optional<Tratamiento> trat = repTratamientos.findById(id);
		Cultivo caux = trat.get().getCultivo();
		caux.getTratamientos().remove(trat.get());
		repCultivos.save(caux);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//convert String to LocalDate
		LocalDate localDate = LocalDate.parse(fechaRealizacion, formatter);
		Producto paux = repProductos.findBynombre(producto);
		Cultivo caux2 = repCultivos.findByespecie(cultivo);
		Long reentrada = Long.valueOf(paux.getPlazoReentrada());
		Long recoleccion = Long.valueOf(paux.getPlazoRecoleccion());
		LocalDate fechaReentrada = localDate.plusDays(reentrada);
		LocalDate fechaRecoleccion = localDate.plusDays(recoleccion);
		trat.get().setCultivo(caux2);
		trat.get().setProducto(paux);
		trat.get().setNumeroLote(numeroLote);
		trat.get().setFechaPlazoReentrada(fechaReentrada);
		trat.get().setFechaPlazoRecoleccion(fechaRecoleccion);
		trat.get().setFechaRealizacion(localDate);
		repTratamientos.save(trat.get());
		caux2.getTratamientos().add(trat.get());
		repCultivos.save(caux2);
		return "tratamientoeditado";
	}
	@RequestMapping("/productos/{id}")
	public String mainProducto(@PathVariable long id, Model model) {
		Optional<Producto> producto = repProductos.findById(id);
		if(producto.isPresent()) {
			model.addAttribute("producto",producto.get());
			return "mainproducto";
		}
		return "Productos";
	}
	
	@RequestMapping("/cultivosOrdenados")
	public String ordenarCultivos(Model model) {
		List<Cultivo> lista = repCultivos.findByOrderByEspecie();
		model.addAttribute("cultivos", lista);
		return "Cultivos";
	}
	
	@RequestMapping("/tratamientoOrdenadosPorReentrada")
	public String ordenarTratamientosReentrada(Model model) {
		List<Tratamiento> lista = repTratamientos.findByOrderByFechaPlazoReentrada();
		model.addAttribute("tratamientos", lista);
		return "Tratamientos";
	}
	
	@RequestMapping("/tratamientoOrdenadosPorRecoleccion")
	public String ordenarTratamientosRecoleccion(Model model) {
		List<Tratamiento> lista = repTratamientos.findByOrderByFechaPlazoRecoleccion();
		model.addAttribute("tratamientos", lista);
		return "Tratamientos";
	}
	
	@RequestMapping("/filtradoPlazos")
	public String prueba(Model model, @RequestParam String fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(fecha, formatter);
		List<Tratamiento> lista = repTratamientos.findByFechaPlazoReentradaOrFechaPlazoRecoleccionGreaterThan(localDate,localDate);
		model.addAttribute("tratamientos", lista);
		return "Tratamientos";
	}
	
	@RequestMapping("/editarproducto/{id}")
    public String editarProducto(@PathVariable long id, Model model) {
        model.addAttribute("id",id);
        return "editarproducto";
    }
    @RequestMapping("/productoeditado")
    public String productoEditado(@RequestParam String nombre,@RequestParam String descripcion,@RequestParam int plazoReentrada,@RequestParam int plazoRecoleccion,@RequestParam Long id, Model model) {
        Optional<Producto> producto = repProductos.findById(id);
        producto.get().setNombre(nombre);
        producto.get().setDescripcion(descripcion);
        producto.get().setPlazoRecoleccion(plazoRecoleccion);
        producto.get().setPlazoReentrada(plazoReentrada);
        repProductos.save(producto.get());
        return "productoeditado";
    }
    @RequestMapping("/añadirproducto")
    public String añadirProducto(Model model) {
        return "añadirproducto";
    }
    @RequestMapping("/productoañadido")
    public String productoAñadido(@RequestParam String nombre,@RequestParam String descripcion,@RequestParam int plazoReentrada,@RequestParam int plazoRecoleccion, Model model) {
        Producto p = new Producto(nombre,descripcion,plazoReentrada,plazoRecoleccion);
        repProductos.save(p);
        return "productoañadido";
    }
}

