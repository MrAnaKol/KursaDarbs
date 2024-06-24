package lv.venta.controller;

import java.util.ArrayList;

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
import lv.venta.model.Uzlabojumi;
import lv.venta.service.IDalibnieksCRUDService;
import lv.venta.service.IUzlabojumiService;

@Controller
public class UzlabojumiController {
	
	@Autowired
    private IDalibnieksCRUDService dalibnieksService;
	
	@Autowired
    private IUzlabojumiService uzlabojumiService;
	
	@GetMapping("/upgrades/{id}") // localhost:8080/upgrades/1
    public String upgradePage(@PathVariable("id") int id, Model model) {
		try {
			Dalibnieks iegutaisDalibnieks = dalibnieksService.izveletiesDalibniekuPecId(id);
			ArrayList<Uzlabojumi> iegutieUzlabojumi = uzlabojumiService.izveletiesVisusNeizmantotusUzlabojumus(id);
			model.addAttribute("dalibnieks", iegutaisDalibnieks);
			model.addAttribute("uzlabojumi", iegutieUzlabojumi);
			model.addAttribute("uzlabojums", new Uzlabojumi()); 
	        return "uzlabojumi-page"; 
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
            return "error-page";
		}
    	
    }
	    
    @PostMapping("/upgrades/{id}")
    public String postUpgradePage(@PathVariable("id") int id,
	        @Valid @ModelAttribute("uzlabojums") Uzlabojumi uzlabojums, BindingResult uzlabojumsResult,
	        Model model) {
    	if (uzlabojumsResult.hasErrors()) {
    		try {
            	return "redirect:/upgrades/" + id;
    		} catch (Exception e) {
    			model.addAttribute("errormsg", e.getMessage());
                return "error-page";
    		}
        }
        try {
        	Uzlabojumi nopirktaisUzlabojums = uzlabojumiService.izveletiesUzlabojumuPecNosaukumaUnCenas(uzlabojums.getNosaukums(), uzlabojums.getCena());
        	uzlabojumiService.nopirktUzlabojumu(nopirktaisUzlabojums.getIdU(), id);
        	return "redirect:/upgrades/" + id;
        } catch (Exception e) {
        	try {
				Dalibnieks iegutaisDalibnieks = dalibnieksService.izveletiesDalibniekuPecId(id);
				ArrayList<Uzlabojumi> iegutieUzlabojumi = uzlabojumiService.izveletiesVisusNeizmantotusUzlabojumus(id);
				model.addAttribute("dalibnieks", iegutaisDalibnieks);
				model.addAttribute("uzlabojumi", iegutieUzlabojumi);
				model.addAttribute("uzlabojums", new Uzlabojumi());
				model.addAttribute("errormsg", e.getMessage());
		        return "uzlabojumi-page"; 
			} catch (Exception er) {
				model.addAttribute("errormsg", er.getMessage());
	            return "error-page";
			}
        }
    }
}
