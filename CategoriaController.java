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

import pe.edu.upc.entity.Categoria;
import pe.edu.upc.service.ICategoriaService;

@Controller
@SessionAttributes("factura")
@RequestMapping("/facturas")
public class CategoriaController {

	@Autowired
	private ICategoriaService cService;





	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "bienvenido";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/nuevo")
	public String nuevoCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		model.addAttribute("listaCategorias", cService.listar());
		model.addAttribute("valorBoton", "Registrar");
		return "/categoria/categoria";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/guardar")
	public String guardarCategoria(@Valid Categoria categoria, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes redirAttrs) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaCategorias", cService.listar());
			model.addAttribute("valorBoton", "Registrar");
			return "/categoria/categoria";
		} else {
			int rpta = -1;
			Optional<Categoria> categoriaencontrado = cService.listarId(categoria.getIdCategoria());
			if (!categoriaencontrado.isPresent()) {
				rpta = cService.insertar(categoria);
				redirAttrs.addFlashAttribute("mensaje", "Se registr\u00f3 correctamente");
				if (rpta > 0) {
					model.addAttribute("valorBoton", "Registrar");
					status.setComplete();
					return "/factura/factura";
				}
			} else {
				cService.modificar(categoria);
				rpta = 1;
				redirAttrs.addFlashAttribute("mensaje", "Se modific\u00f3 correctamente");
			}

		}
		model.addAttribute("listaCategorias", cService.listar());

		return "redirect:/categorias/listar";

	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/listar")
	public String listarCategorias(Model model) {
			try {
				model.addAttribute("categoria", new Categoria());
				model.addAttribute("listaCategorias", cService.listar());
			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
			}
			return "/categoria/listaCategoria";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirAttrs) {
		try {
			if (id != null && id > 0) {
				cService.eliminar(id);
				redirAttrs.addFlashAttribute("mensaje", "Se cancel\u00f3 la categoria");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede anular la categoria");
		}
		redirAttrs.addFlashAttribute("listaCategorias", cService.listar());

		return "redirect:/categorias/listar";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/detalle/{id}") // modificar
	public String detailsCategoria(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Categoria> categoria = cService.listarId(id);
			if (!categoria.isPresent()) {
				model.addAttribute("info", "categoria no existe");
				return "redirect:/categorias/listar";
			} else {
				model.addAttribute("categoria", categoria.get());
				model.addAttribute("listaCategorias", cService.listar());

			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("valorBoton", "Modificar");
		return "/factura/factura";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Categoria> categoria = cService.listarId(id);
		if (categoria == null) {
			flash.addFlashAttribute("error", "La categoria no existe en la base de datos");
			return "redirect:/categorias/listar";
		}
		model.put("categoria", categoria.get());
		return "categoria/verc";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Categoria categoria) throws ParseException {

		List<Categoria> listaCategorias;
		listaCategorias = cService.findByNombreCategoria(categoria.getNombreCategoria());
		if (listaCategorias.isEmpty()) {
			model.put("mensaje", "No se encontr\u00f3 al contador con el nombre especificado");
		}
		model.put("listaCategorias", listaCategorias);
		return "categoria/listaCategoria";
	}

}
