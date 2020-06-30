package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Seleccion;
import pe.edu.upc.service.ISeleccionService;

@Controller
@SessionAttributes("contador")
@RequestMapping("/contadores")
public class SeleccionController {
	@Autowired
	private ISeleccionService sService;

	@RequestMapping("/bienvenido")
	public String goHome() {
		return "bienvenido";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/nuevo")
	public String addSeleccion(Model model) {
		model.addAttribute("seleccion", new Seleccion());
		model.addAttribute("listaSeleccion", sService.list());
		model.addAttribute("valorBoton", "Registrar");
		return "seleccion/seleccion";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/guardar")
	public String saveAccountant(@Valid Seleccion seleccion, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes redirAttrs) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("valorBoton", "Registrar");
			return "/seleccion/seleccion";
		} else {
			int rpta = -1;
			Optional<Seleccion> seleccionEncontrado = sService.listId(seleccion.getIdSeleccion());
			if (!seleccionEncontrado.isPresent()) {
				rpta = sService.insert(seleccion);
				redirAttrs.addFlashAttribute("mensaje", "Se registr\u00f3 correctamente");
				if (rpta > 0) {
					model.addAttribute("mensaje", "Ya existe la seleccion");
					model.addAttribute("valorBoton", "Registrar");
					status.setComplete();
					return "/seleccion/seleccion";
				}

			} else {
				sService.modify(seleccion);
				rpta = 1;
				status.setComplete();
				redirAttrs.addFlashAttribute("mensaje", "Se modific\u00f3 correctamente.");
			}

		}
		model.addAttribute("listaSelecciones", sService.list());

		return "redirect:/selecciones/listar";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/listar")
	public String listarSelecciones(Model model) {
		try {
			model.addAttribute("seleccion", new Seleccion());
			model.addAttribute("listaSelecciones", sService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/seleccion/listaSeleccion";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirAttrs) {
		try {
			if (id != null && id > 0) {
				sService.delete(id);
				redirAttrs.addFlashAttribute("mensaje", "Se cancel\u00f3 la seleccion");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirAttrs.addFlashAttribute("mensaje", "No se puede anular la seleccion seleccionada");
		}
		model.put("listaSelecciones", sService.list());

		return "redirect:/selecciones/listar";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/detalle/{id}") // modificar
	public String detailsContador(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Seleccion> seleccion = sService.listId(id);
			if (!seleccion.isPresent()) {
				model.addAttribute("info", "Contador no existe");
				return "redirect:/selecciones/listar";
			} else {
				model.addAttribute("seleccion", seleccion.get());

			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("valorBoton", "Modificar");
		return "/seleccion/seleccion";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Seleccion seleccion) throws ParseException {

		List<Seleccion> listaSelecciones;

		seleccion.setNombreSeleccion(seleccion.getNombreSeleccion());
		listaSelecciones = sService.buscarNombre(seleccion.getNombreSeleccion());
		if (listaSelecciones.isEmpty()) {
			model.put("mensaje", "No se encontr\u00f3 la seleccion");
		}
		model.put("listaSelecciones", listaSelecciones);
		return "seleccion/listaSeleccion";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Seleccion> seleccion = sService.listId(id);
		if (seleccion == null) {
			flash.addFlashAttribute("error", "La seleccion no existe en la base de datos");
			return "redirect:/selecciones/listar";
		}
		model.put("seleccion", seleccion.get());

		return "seleccion/vers";
	}
}
