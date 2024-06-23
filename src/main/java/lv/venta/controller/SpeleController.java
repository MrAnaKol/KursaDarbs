package lv.venta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lv.venta.model.Dalibnieks;

@Controller
public class SpeleController {
	@GetMapping("/game/{id}") // localhost:8080/game/1
    public String GamePage(@PathVariable("id") int id, Model model) {
		try {
	        return "spele-page"; 
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
            return "error-page";
		}
    	
    }
    
	@PostMapping("/game/{id}")
	public String postGamePage(@Valid Dalibnieks dalibnieks, @PathVariable("id") int id, BindingResult result, Model model) {
		if (result.hasErrors()) {
	    	model.addAttribute("dalibnieks",dalibnieks);
	        return "profils-page";
	    }
	    try {
	    	return "profils-page";
	    } catch (Exception e) {
	        model.addAttribute("errormsg", e.getMessage());
	        return "error-page";
	    }
	}
}

