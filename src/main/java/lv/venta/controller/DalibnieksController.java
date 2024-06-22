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

    @GetMapping("/register") // localhost:8080/register
    public String showRegistrationForm(Model model) {
        model.addAttribute("dalibnieks", new Dalibnieks());
        return "registreties-page";
    }

    @PostMapping("/register")
    public String registerUser(@Valid Dalibnieks dalibnieks, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("dalibnieks",dalibnieks);
        	System.out.println(result);
            return "registreties-page";
        }
        try {
            dalibnieksService.jaunsDalibnieks(dalibnieks.getLoma(), dalibnieks.getLietotajvards(), dalibnieks.getParole());
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("errormsg", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/login") // localhost:8080/login
    public String showLoginForm() {
        return "ieiet-page";
    }

    /*@PostMapping("/login")
    public String loginUser(@RequestParam("lietotajvārds") String lietotajvards, @RequestParam("parole") String parole, Model model) {
        try {
            Dalibnieks dalibnieks = dalibnieksService.esosaislietotajs(lietotajvards, parole);
            if (dalibnieks != null) {

                return "redirect:/sakums";
            } else {
                model.addAttribute("errormsg", "Nepareizs lietotājvārds vai parole");
                return "ieiet-page";
            }
        } catch (Exception e) {
            model.addAttribute("errormsg", e.getMessage());
            return "error-page";
        }
    }
*/
    @GetMapping("/sakums") // localhost:8080/sakums
    public String sakumsPage(Model model) {

        return "sakums-page"; 
    }
}
