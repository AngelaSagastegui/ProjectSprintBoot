package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.model.Producto;
import pe.edu.upc.service.IProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	private IProductoService pService;
	
	@RequestMapping("/bienvenido")
	public String irProductoBienvenido() {		
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irProducto(Map<String, Object>model) {
		model.put("listaProductos", pService.listar());
		return "listProducto";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{
		model.addAttribute("producto", new Producto());
		return "producto";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Producto objProducto, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			return "producto";
		}
		else {
				boolean flag = pService.insertar(objProducto);
				if (flag) {
					return "redirect:/producto/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/producto/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Producto objProducto, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/producto/listar";
		}
		else {
			boolean flag = pService.modificar(objProducto);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/producto/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/producto/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Producto> objProducto = pService.listarId(id);
		if (objProducto == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/producto/listar";
		}
		else {
			model.addAttribute("producto", objProducto);
			return "producto";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				pService.eliminar(id);
				model.put("listaProductos", pService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaProductos", pService.listar());
		}
		return "listProducto";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaProductos", pService.listar());
		return "listProducto";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Producto producto) 
	throws ParseException
	{
		pService.listarId(producto.getIdProducto());
		return "listProducto";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Producto producto) 
			throws ParseException
	{
		List<Producto> listaProductos;
		producto.setNombreProducto(producto.getNombreProducto());
		listaProductos = pService.buscarNombre(producto.getNombreProducto());
		
		if (listaProductos.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaProductos", listaProductos);
		return "buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("producto", new Producto());
		return "buscar";
	}
		

}
