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

import pe.edu.upc.entity.Tipo;
import pe.edu.upc.service.ITipoService;

@Controller
@SessionAttributes("recurso")
@RequestMapping("/recursos")
public class TipoController {
	@Autowired
	private ITipoService tService;

	

	@GetMapping("/bienvenido")
	public String bienvenido(Model model) {
		return "bienvenido";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/nuevo")
	public String nuevoRecurso(Model model) {
		model.addAttribute("tipo", new Tipo());
		model.addAttribute("listaTipos", tService.listar());
		model.addAttribute("valorBoton", "Registrar");
		return "tipo/tipo";
	}


	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@PostMapping("/guardar")
	public String guardarTipo(@Valid Tipo tipo, BindingResult result, Model model,
			SessionStatus status, RedirectAttributes redirAttrs) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaTipos", tService.listar());
			model.addAttribute("valorBoton", "Registrar");
			return "/listaTipo/listaTipo";
		} else {
			int rpta = -1;
			Optional<Tipo> tipoEncontrado = tService.listarId(tipo.getIdTipo());
			if (!tipoEncontrado.isPresent()) {
				rpta = tService.insertar(tipo);
				redirAttrs.addFlashAttribute("mensaje", "Se registr\u00f3 correctamente");
				if (rpta > 0) {
					model.addAttribute("valorBoton", "Registrar");
					status.setComplete();
					return "/listaTipo/listaTipo";
				}

			} else {
				tService.modificar(tipo);
				rpta = 1;
				status.setComplete();
				redirAttrs.addFlashAttribute("mensaje", "Se modific\u00f3 correctamente");
			}
		}
		model.addAttribute("listaTipos", tService.listar());

		return "redirect:/listaTipos/listar";
	}


	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/listar")
	public String listarTipos(Model model) {
		try {
			model.addAttribute("tipo", new Tipo());

			model.addAttribute("listaTipos", tService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/recurso/listaTipo";
	}

	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirAttrs) {
		try {
			if (id != null && id > 0) {
				tService.eliminar(id);
				redirAttrs.addFlashAttribute("mensaje", "Se elimin\u00f3 el tipo");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirAttrs.addFlashAttribute("mensaje", "No se puede eliminar el tipo seleccionado");
		}
		model.put("listaTipos", tService.listar());

		return "redirect:/tipos/listar";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/detalle/{id}") // modificar
	public String detailsTipos(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Tipo> tipo  = tService.listarId(id);
			if (tipo == null) {
				model.addAttribute("info", "Recurso no existe");
				return "redirect:/tipos/listar";
			} else {
				model.addAttribute("tipo", tipo);
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("valorBoton", "Modificar");
		return "/tipo/tipo";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Tipo tipo) throws ParseException {

		List<Tipo> listaTipos;

		tipo.setNombreTipo(tipo.getNombreTipo());
		listaTipos = tService.buscarNombre(tipo.getNombreTipo());

		if (listaTipos.isEmpty()) {
			listaTipos = tService.buscarNombre(tipo.getNombreTipo());
		}

		if (listaTipos.isEmpty()) {
			model.put("mensaje", "No se encontr\u00f3");
		}
		model.put("listaTipos", listaTipos);
		return "tipo/listaTipo";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Tipo> tipo = tService.listarId(id);
		if (tipo == null) {
			flash.addFlashAttribute("error", "El tipo no existe en la base de datos");
			return "redirect:/tipos/listar";
		}
		model.put("tipo", tipo);

		return "tipo/vert";
	}

}
