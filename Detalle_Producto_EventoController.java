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

import pe.edu.upc.entity.Detalle_Producto_Evento;
import pe.edu.upc.entity.Evento;
import pe.edu.upc.entity.Producto;
import pe.edu.upc.service.IDetalle_Producto_EventoService;
import pe.edu.upc.service.IEventoService;
import pe.edu.upc.service.IProductoService;

@Controller
@SessionAttributes("detalle")
@RequestMapping("/detalles")
public class Detalle_Producto_EventoController {

	@Autowired
	private IDetalle_Producto_EventoService dService;
	@Autowired
	private IEventoService eService;
	@Autowired
	private IProductoService pService;

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "bienvenido";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/nuevo")
	public String nuevoDetalle(Model model) {
		model.addAttribute("listaProductos", pService.listar());
		model.addAttribute("listaEventos", eService.listar());
		model.addAttribute("producto", new Producto());
		model.addAttribute("evento", new Evento());
		model.addAttribute("detalle", new Detalle_Producto_Evento());
		return "detalle/detalle";
	}


	@Secured("ROLE_ADMIN")
	@PostMapping("/guardar")
	public String guardarDetalle(@Valid @ModelAttribute(value = "detalle") Detalle_Producto_Evento detalle,
			BindingResult result, Model model, SessionStatus status, RedirectAttributes redirAttrs) throws Exception {

		if (result.hasErrors()) {
			model.addAttribute("listaProductos", pService.listar());
			model.addAttribute("listaEventos", eService.listar());
			model.addAttribute("producto", new Producto());
			model.addAttribute("evento", new Evento());
			model.addAttribute("detalle", new Detalle_Producto_Evento());
			model.addAttribute("valorBoton", "Registrar");
			return "/detalle/detalle";
		} else {
			int rpta = -1;
			Optional<Detalle_Producto_Evento> detalleEncontrado = dService.listarId(detalle.getIdDetalle());
			if (!detalleEncontrado.isPresent()) {
				rpta = dService.insertar(detalle);
				redirAttrs.addFlashAttribute("mensaje", "Se registr\u00f3 correctamente");
				if (rpta > 0) {
					model.addAttribute("valorBoton", "Registrar");
					status.setComplete();
					return "/detalle/detalle";
				}

			} else {
				dService.modificar(detalle);
				rpta = 1;
				status.setComplete();
				redirAttrs.addFlashAttribute("mensaje", "Se modific\u00f3 correctamente");
			}

		}
		model.addAttribute("listaDetalles", dService.listar());

		return "redirect:/detalles/listar";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/listar")
	public String listarDetalles(Model model) {
		try {
			model.addAttribute("detalle", new Detalle_Producto_Evento());
			model.addAttribute("listaDetalles", dService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/detalle/listaDetalle";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {

		try {
			if (id != null && id > 0) {
				dService.eliminar(id);
				model.put("mensaje", "Se ha eliminado el detalle del producto y evento correctamente.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar el detalle del producto y evento seleccionado.");
		}
		model.put("listaDetalles", dService.listar());

		return "redirect:/detalles/listar";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/detalle/{id}")
	public String detailsDetalle(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Detalle_Producto_Evento> detalle = dService.listarId(id);
			if (!detalle.isPresent()) {
				model.addAttribute("info", "Detalle no existe");
				return "redirect:/detalles/listar";
			} else {
				model.addAttribute("listaProductos", pService.listar());
				model.addAttribute("listaEventos", eService.listar());
				model.addAttribute("producto", new Producto());
				model.addAttribute("evento", new Evento());

				model.addAttribute("detalle", detalle.get());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		model.addAttribute("valorBoton", "Modificar");

		return "/detalle/detalle";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Detalle_Producto_Evento detalle) throws ParseException {

		List<Detalle_Producto_Evento> listaDetalles;

		detalle.setCantidadDetalle(detalle.getCantidadDetalle());
		listaDetalles = dService.buscarDetalle(detalle.getIdDetalle());
		if (listaDetalles.isEmpty()) {
			model.put("mensaje", "No se encontraron recursos con la cantidad de unidades especificado.");

		}
		model.put("listaDetalles", listaDetalles);
		return "detalle/listaDetalle";
	}
	
	

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Detalle_Producto_Evento> detalle = dService.listarId(id);
		if (detalle == null) {
			flash.addFlashAttribute("error", "El detalle de prodcutos y eventos no existe en la base de datos.");
			return "redirect:/detalles/listar";
		}
		model.put("detalle", detalle.get());

		return "detalle/verd";
	}

}
