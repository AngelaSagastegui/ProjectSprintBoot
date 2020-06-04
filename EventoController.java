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

import pe.edu.upc.model.Evento;
import pe.edu.upc.service.IEventoService;

@Controller
@RequestMapping("/evento")
public class EventoController {
	
	@Autowired
	private IEventoService eService;

	@RequestMapping("/bienvenido")
	public String irEventoBienvenido() {		
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irEvento(Map<String, Object>model) {
		model.put("listaEventos", eService.listar());
		return "listEvento";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{
		model.addAttribute("evento", new Evento());
		return "evento";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Evento objEvento, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			return "evento";
		}
		else {
				boolean flag = eService.insertar(objEvento);
				if (flag) {
					return "redirect:/evento/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/evento/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Evento objEvento, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/evento/listar";
		}
		else {
			boolean flag = eService.modificar(objEvento);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/evento/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/evento/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Evento> objEvento = eService.listarId(id);
		if (objEvento == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/evento/listar";
		}
		else {
			model.addAttribute("evento", objEvento);
			return "evento";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				eService.eliminar(id);
				model.put("listaEventos", eService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaEventos", eService.listar());
		}
		return "listEvento";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaEventos", eService.listar());
		return "listEvento";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Evento evento) 
	throws ParseException
	{
		eService.listarId(evento.getIdEvento());
		return "listEvento";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Evento evento) 
			throws ParseException
	{
		List<Evento> listaEventos;
		evento.setNombreEvento(evento.getNombreEvento());
		listaEventos = eService.buscarNombre(evento.getNombreEvento());
		
		if (listaEventos.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaEventos", listaEventos);
		return "buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("evento", new Evento());
		return "buscar";
	}
}