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
public class UzlabojumiController {
	 @GetMapping("/upgrades/{id}") // localhost:8080/upgrades/1
	    public String upgradePage(@PathVariable("id") int id, Model model) {
			try {
		        return "uzlabojumi-page"; 
			} catch (Exception e) {
				model.addAttribute("errormsg", e.getMessage());
	            return "error-page";
			}
	    	
	    }
	    
    @PostMapping("/upgrades/{id}")
    public String postUpgradePage(@Valid Dalibnieks dalibnieks, @PathVariable("id") int id, BindingResult result, Model model) {
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
