package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lv.venta.model.Dalibnieks;
import lv.venta.service.IDalibnieksCRUDService;

@Controller
public class DalibnieksController {
    
    @Autowired
    private IDalibnieksCRUDService dalibnieksService;
    
    @GetMapping("/sakums") // localhost:8080/sakums
    public String sakumsPage() {
        return "sakums-page";
    } 
    
    @GetMapping("/register") // localhost:8080/register
    public String showRegistrationForm(Model model) {
        model.addAttribute("dalibnieks", new Dalibnieks());
        return "registreties-page";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute Dalibnieks dalibnieks, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("dalibnieks", dalibnieks);
            return "registreties-page";
        }
        try {
            dalibnieksService.jaunsDalibnieks(dalibnieks.getLoma(), dalibnieks.getLietotajvards(), dalibnieks.getParole());
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("errormsg", e.getMessage());
            return "registreties-page";
        }
    }

    @GetMapping("/login") // localhost:8080/login
    public String showLoginForm(Model model) {
    	model.addAttribute("dalibnieks", new Dalibnieks());
        return "ieiet-page";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute Dalibnieks dalibnieks, BindingResult result, Model model) {
    	if (result.hasErrors()) {
        	model.addAttribute("dalibnieks", dalibnieks);
        	//System.out.println(result);
            return "ieiet-page";
        }
        try {
            Dalibnieks iegutaisDalibnieks = dalibnieksService.izveletiesDalibniekuPecLietotajvardaUnParoles(dalibnieks.getLietotajvards(), dalibnieks.getParole());
            if (iegutaisDalibnieks != null) {
                return "redirect:/profils/" + iegutaisDalibnieks.getIdD();
            } else {
            	model.addAttribute("dalibnieks", dalibnieks);
                return "ieiet-page";
            }
        } catch (Exception e) {
            model.addAttribute("errormsg", e.getMessage());
            return "error-page";
        }
    }
    
    @GetMapping("/logout") // localhost:8080/logout
    public String logout() {
    	return "redirect:/login";
    }
    
    @GetMapping("/profils/{id}") // localhost:8080/profils/1
    public String profilPage(@PathVariable("id") int id, Model model) {
		try {
			Dalibnieks iegutaisDalibnieks = dalibnieksService.izveletiesDalibniekuPecId(id);
			model.addAttribute("dalibnieks", iegutaisDalibnieks);
	        return "profils-page"; 
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
            return "error-page";
		}
    }
    
    @PostMapping("/profils/{id}")
    public String changePassword(@Valid @ModelAttribute Dalibnieks dalibnieks, BindingResult result, @PathVariable("id") int id, Model model) {
    	System.out.println(result);
    	if (result.hasErrors()) {
			try {
				Dalibnieks iegutaisDalibnieks;iegutaisDalibnieks = dalibnieksService.izveletiesDalibniekuPecId(id);
				model.addAttribute("dalibnieks", iegutaisDalibnieks);
				model.addAttribute("errormsg", "Parolei ir jābūt vismaz 8 simbolus garai un jāsatur vismaz vienu burtu, vienu ciparu un vienu speciālo rakstzīmi!");
	            return "profils-page";
			} catch (Exception e) {
				model.addAttribute("errormsg", e.getMessage());
	            return "error-page";
			}
        }
        try {
        	dalibnieksService.atjaunotDalibniekuPecId(id, dalibnieks.getLoma(), dalibnieks.getLietotajvards(), dalibnieks.getParole());
        	Dalibnieks iegutaisDalibnieks = dalibnieksService.izveletiesDalibniekuPecId(id);
			model.addAttribute("dalibnieks", iegutaisDalibnieks);
        	return "profils-page";
        } catch (Exception e) {
            model.addAttribute("errormsg", e.getMessage());
            return "error-page";
        }
    }
}
