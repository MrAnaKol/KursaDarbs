package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lv.venta.model.Dalibnieks;
import lv.venta.service.IDalibnieksCRUDService;
import lv.venta.service.ISasniegumiService;

@Controller
public class SpeleController {
	
	@Autowired
    private IDalibnieksCRUDService dalibnieksService;
	
	@Autowired
    private ISasniegumiService sasniegumiService;
	
	@GetMapping("/game/{id}") // localhost:8080/game/1
    public String gamePage(@PathVariable("id") int id, Model model) {
		try {
			sasniegumiService.autonomasPupinas(id);
			Dalibnieks iegutaisDalibnieks = dalibnieksService.izveletiesDalibniekuPecId(id);
			model.addAttribute("dalibnieks", iegutaisDalibnieks);
	        return "spele-page"; 
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
            return "error-page";
		}
    	
    }
    
	@PostMapping("/game/{id}")
	public String postGamePage(@Valid @ModelAttribute Dalibnieks dalibnieks, @PathVariable("id") int id, BindingResult result, Model model) {
		if (result.hasErrors()) {
	    	model.addAttribute("dalibnieks",dalibnieks);
	        return "profils-page";
	    }
	    try {
	    	sasniegumiService.tiekUzspiestsUzKoka(id);
	    	return "redirect:/game/" + id;
	    } catch (Exception e) {
	        model.addAttribute("errormsg", e.getMessage());
	        return "error-page";
	    }
	}
}
