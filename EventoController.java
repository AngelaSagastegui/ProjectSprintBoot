package pe.edu.upc.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Evento;
import pe.edu.upc.service.IEventoService;

@Controller
@SessionAttributes("listaCompra")
@RequestMapping("/listaCompras")
public class EventoController {

	@Autowired
	private IEventoService eService;

	@GetMapping("/bienvenido")
	public String bienvenido(Model model) {
		return "bienvenido";
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/nuevo")
	public String nuevaEvento(Model model) {
		model.addAttribute("evento", new Evento());
		model.addAttribute("listaEventos", eService.listar());
		model.addAttribute("valorBoton", "Registrar");
		return "/evento/evento";
	}

	@PostMapping("/guardar")
	public String guardarEvento(@Valid Evento evento, BindingResult result, Model model,
			SessionStatus status, RedirectAttributes redirAttrs) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaEventos", eService.listar());
			model.addAttribute("valorBoton", "Registrar");
			return "/listaEvento/listaEvento";
		} else {
			int rpta = -1;
			Optional<Evento> eventoEncontrado = eService.listarId(evento.getIdEvento());
			if (!eventoEncontrado.isPresent()) {
				rpta = eService.insertar(evento);
				redirAttrs.addFlashAttribute("mensaje", "Se registr\u00f3 correctamente");
				if (rpta > 0) {
					model.addAttribute("valorBoton", "Registrar");
					status.setComplete();
					return "/listaEvento/listaEvento";
				}

			} else {
				eService.modificar(evento);
				rpta = 1;
				status.setComplete();
				redirAttrs.addFlashAttribute("mensaje", "Se modific\u00f3 correctamente");
			}
		}
		model.addAttribute("listaEventos", eService.listar());

		return "redirect:/listaEventos/listar";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/listar")
	public String listarEvento(Model model) {
		try {
			model.addAttribute("evento", new Evento());
			model.addAttribute("listaEventos", eService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/evento/listaEvento";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirAttrs) {
		try {
			if (id != null && id > 0) {
				eService.eliminar(id);
				redirAttrs.addFlashAttribute("mensaje", "Se cancel\u00f3 el evento");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede anular la categoria");
		}
		redirAttrs.addFlashAttribute("listaEventos", eService.listar());

		return "redirect:/eventos/listar";
	
	}

/*	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping("/buscarp")
	public String buscarProveedor(Map<String, Object> model, @ModelAttribute Evento lista) throws ParseException {

		List<Evento> listaListas;
		lista.setNotaLista(lista.getNotaLista());
		listaListas = lService.buscar(lista.getNotaLista());
		if (listaListas.isEmpty()) {
			listaListas = lService.buscarProveedor(lista.getNotaLista());
		}
		if (listaListas.isEmpty()) {
			listaListas = lService.buscarEstado(lista.getNotaLista());
		}
		if (listaListas.isEmpty()) {
			model.put("mensaje", "No se encontr\u00f3 ning\u00fan resultado");
		}

		model.put("listaLista_Compras", listaListas);
		return "listaCompra/listaListaCompra";

	}

	*/

	@Secured("ROLE_ADMIN")
	@GetMapping("/detalle/{id}")
	public String detailsEvento(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Evento> evento = eService.listarId(id);
			if (!evento.isPresent()) {
				model.addAttribute("info", "Lista de Compra no existe");
				return "redirect:/eventos/listar";
			} else {
				model.addAttribute("evento", evento.get());
				model.addAttribute("listaEventos", eService.listar());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		model.addAttribute("valorBoton", "Modificar");

		return "/listaEvento/listaEvento";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Evento> evento = eService.listarId(id);
		if (evento == null) {
			flash.addFlashAttribute("error", "La lista de compra no existe en la base de datos.");
			return "redirect:/listaCompras/listar";
		}
		model.put("evento", evento.get());
		return "listaEvento/vere";
	}

}
