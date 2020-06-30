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

import pe.edu.upc.entity.Producto;
import pe.edu.upc.service.IProductoService;

@Controller
@SessionAttributes("proveedor")
@RequestMapping("/proveedores")
public class ProductoController {

	@Autowired
	private IProductoService pService;

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "bienvenido";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/nuevo")
	public String nuevoProducto(Model model) {
		model.addAttribute("producto", new Producto());
		model.addAttribute("listaProductos", pService.listar());
		model.addAttribute("valorBoton", "Registrar");
		return "producto/producto";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/guardar")
	public String guardarProveedor(@Valid Producto producto, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes redirAttrs) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("valorBoton", "Registrar");
			return "/producto/producto";
		} else {
			int rpta = -1;
			Optional<Producto> productoEncontrado = pService.listarId(producto.getIdProducto());
			if (!productoEncontrado.isPresent()) {
				rpta = pService.insertar(producto);
				redirAttrs.addFlashAttribute("mensaje", "Se registr\u00f3 correctamente");
				if (rpta > 0) {
					model.addAttribute("mensaje", "Ya existe un producto");
					model.addAttribute("valorBoton", "Registrar");
					status.setComplete();
					return "/producto/producto";
				}
			} else {
				pService.modificar(producto);
				if (rpta > 0) {
					model.addAttribute("mensaje", "Ya existe un proveedor con el mismo RUC");
					model.addAttribute("valorBoton", "Modificar");
					status.setComplete();
					return "/producto/producto";
				}
				redirAttrs.addFlashAttribute("mensaje", "Se modific\u00f3 correctamente");
			}

		}
		model.addAttribute("listaProductos", pService.listar());

		return "redirect:/productos/listar";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/listar")
	public String listarProductos(Model model) {
		try {
			model.addAttribute("producto", new Producto());
			model.addAttribute("listaProductos", pService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/producto/listaProducto";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirAttrs) {
		try {
			if (id != null && id > 0) {
				pService.eliminar(id);
				redirAttrs.addFlashAttribute("mensaje", "se cancel\u00f3 el producto seleccionado");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirAttrs.addFlashAttribute("mensaje", "No se puede anular el producto seleccionado");
		}
		model.put("listaProductos", pService.listar());
		return "redirect:/productos/listar";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/detalle/{id}")
	public String detailsProductos(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Producto> producto = pService.listarId(id);
			if (!producto.isPresent()) {
				model.addAttribute("info", "producto no existe");
				return "redirect:/productos/listar";
			} else {
				model.addAttribute("producto", producto.get());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("valorBoton", "Modificar");
		return "/producto/producto";
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Producto producto) throws ParseException {

		List<Producto> listaProductos;
		producto.setNombreProducto(producto.getNombreProducto());
		listaProductos = pService.buscarNombre(producto.getNombreProducto());
		if (listaProductos.isEmpty()) {
			model.put("mensaje", "No se encontr\u00f3 al producto favorito");
		}
		model.put("listaProductos", listaProductos);
		return "producto/listaProducto";
	}

	

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Producto> producto = pService.listarId(id);
		if (producto == null) {
			flash.addFlashAttribute("error", "El producto no existe en la base de datos");
			return "redirect:/productos/listar";
		}
		model.put("producto", producto.get());

		return "producto/verpr";
	}

}
