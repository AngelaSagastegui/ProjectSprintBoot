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

import pe.edu.upc.entity.Personalizacion;
import pe.edu.upc.serviceimpl.PersonalizacionServiceImpl;

@Controller
@SessionAttributes("empleado")
@RequestMapping("/empleados")
public class PersonalizacionController {

	@Autowired
	private PersonalizacionServiceImpl eService;
	


	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "bienvenido";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/nuevo")
	public String nuevaPersonalizacion(Model model) {
		model.addAttribute("personalizacion", new Personalizacion());
		model.addAttribute("listaPersonalizaciones", eService.listar());
		model.addAttribute("valorBoton", "Registrar");
		return "personalizacion/personalizacion";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/guardar")
	public String guardarPersonalizacion(@Valid Personalizacion personalizacion, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes redirAttrs) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaPersonalizaciones", eService.listar());
			model.addAttribute("valorBoton", "Registrar");
			return "/listaPersonalizacion/listaPersonalizacion";
		} else {
			int rpta = -1;
			Optional<Personalizacion> personalizacionEncontrado = eService.listarId(personalizacion.getIdPersonalizacion());
			if (!personalizacionEncontrado.isPresent()) {
				rpta = eService.insertar(personalizacion);
				redirAttrs.addFlashAttribute("mensaje", "Se registr\u00f3 correctamente");
				if (rpta > 0) {
					model.addAttribute("valorBoton", "Registrar");
					status.setComplete();
					return "/listaPersonalizacion/listaPersonalizacion";
				}

			} else {
				eService.modificar(personalizacion);
				rpta = 1;
				status.setComplete();
				redirAttrs.addFlashAttribute("mensaje", "Se modific\u00f3 correctamente");
			}
		}
		model.addAttribute("listaPersonalizaciones", eService.listar());

		return "redirect:/listaPersonalizaciones/listar";

	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/listar")
	public String listarPersonalizacion(Model model) {
		try {
			model.addAttribute("personalizacion", new Personalizacion());
			model.addAttribute("listaPersonalizaciones", eService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/personalizacion/listaPersonalizacion";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirAttrs) {
		try {
			if (id != null && id > 0) {
				eService.eliminar(id);
				redirAttrs.addFlashAttribute("mensaje", "Se ha eliminado correctamente.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirAttrs.addFlashAttribute("mensaje", "No se puede eliminar la personalizacion.");
		}
		model.put("listaPersonalizaciones", eService.listar());

		return "redirect:/personalizaciones/listar";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/detalle/{id}") // modificar
	public String DetallesEmpleado(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Personalizacion> personalizacion = eService.listarId(id);
			if (!personalizacion.isPresent()) {
				model.addAttribute("info", "Personalizacion no existe.");
				return "redirect:/personalizaciones/listar";
			} else {
				model.addAttribute("personalizacion", personalizacion.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("valorBoton", "Modificar");
		return "/personalizacion/personalizacion";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping("/buscar")
	public String BuscarPorVino(Map<String, Object> model, @ModelAttribute Personalizacion personalizacion) throws ParseException {

		List<Personalizacion> listaPersonalizaciones;

		personalizacion.setNombreVino(personalizacion.getNombreVino());
		listaPersonalizaciones = eService.buscarNombre(personalizacion.getNombreVino());
		if (listaPersonalizaciones.isEmpty()) {
			model.put("mensaje", "No se encontr\u00f3 al Nombre del Vino en la Personalizacion");
		}
		model.put("listaPersonalizaciones", listaPersonalizaciones);
		return "personalizacion/listaPersonalizacion";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Personalizacion> personalizacion = eService.listarId(id);
		if (personalizacion == null) {
			flash.addFlashAttribute("error", "La perosnalizacion no existe en la base de datos.");
			return "redirect:/personalizaciones/listar";
		}
		model.put("personalizacion", personalizacion.get());

		return "personalizacion/verp";
	}
}
