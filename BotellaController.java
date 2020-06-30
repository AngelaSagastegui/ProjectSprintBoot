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

import pe.edu.upc.entity.Botella;
import pe.edu.upc.service.IBotellaService;

@Controller
@SessionAttributes("botella")
@RequestMapping("/botella")
public class BotellaController {

	@Autowired
	private IBotellaService boService;



	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "bienvenido";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/nuevo")
	public String nuevoBotella(Model model) {
		model.addAttribute("botella", new Botella());
		model.addAttribute("listaBotellas", boService.listar());
		model.addAttribute("valorBoton", "Registrar");
		return "botella/botella";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/guardar")
	public String guardarBotella(@Valid Botella botella, BindingResult result, Model model,
			SessionStatus status, RedirectAttributes redirAttrs) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("valorBoton", "Registrar");
			return "/botella/botella";
		} else {
			int rpta = -1;
			Optional<Botella> botellaencontrada = boService.listarId(botella.getIdTipoBotella());
			if (!botellaencontrada.isPresent()) {
				rpta = boService.insertar(botella);

				redirAttrs.addFlashAttribute("mensaje", "Se registr\u00f3 correctamente");

				if (rpta > 0) {
					model.addAttribute("valorBoton", "Registrar");
					status.setComplete();
					return "/botella/botella";
				}

			} else {
				boService.modificar(botella);
				rpta = 1;
				status.setComplete();
				redirAttrs.addFlashAttribute("mensaje", "Se modific\u00f3 correctamente");
			}

		}
		model.addAttribute("listaBotellas", boService.listar());

		return "redirect:/botellas/listar";

	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/listar")
	public String listarBotella(Model model) {
		try {
			model.addAttribute("botella", new Botella());
			model.addAttribute("listaBotellas", boService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/botella/listaBotella";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/detalle/{id}") // modificar
	public String detailsBotella(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Botella> botella = boService.listarId(id);
			if (!botella.isPresent()) {
				model.addAttribute("info", "botella no existe");
				return "redirect:/botellas/listar";
			} else {
				model.addAttribute("botellas", botella.get());
				model.addAttribute("listaBotellas", boService.listar());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("valorBoton", "Modificar");
		return "/botella/botella";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				boService.eliminar(id);
				model.put("mensaje", "Se cancel\u00f3 el tipo de botella");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede anular el tipo de botella");
		}
		model.put("listaBotellas", boService.listar());

		return this.listarBotella((Model) model);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Botella botella) throws ParseException {
		List<Botella> listaBotellas;
		listaBotellas = boService.findByNombreTipoBotella(botella.getNombreTipoBotella());
		if (listaBotellas.isEmpty()) {
			model.put("mensaje", "No se encontr\u00f3 al nombre del tipo de botella");
		}
		model.put("listaBotellas", listaBotellas);
		return "botella/listaBotella";
	}

}
