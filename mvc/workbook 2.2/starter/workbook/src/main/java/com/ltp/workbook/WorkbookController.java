package com.ltp.workbook;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ltp.workbook.Controller.show;

@Controller
public class WorkbookController {
    
    @GetMapping("/")
    public ModelAndView getShows() {
        // show show1 = new show("Breaking Bad", "Ozymandias", 10.0);
        // show show2 = new show("Attack on Titan", "Hero", 9.9);
        // show show3 = new show("Attack on Titan", "Perfect Game", 9.9);
        // show show4 = new show("Star Wars: The Clone Wars", "Victory and Death", 9.9);
        // show show5 = new show("Mr. Robot", "407 Proxy Authentication Required", 9.9);
        // model.addAttribute("show1", show1);
        // model.addAttribute("show2", show2);
        // model.addAttribute("show3", show3);
        // model.addAttribute("show4", show4);
        // model.addAttribute("show5", show5);
        // return "shows";
        Map<String, show> model = new HashMap<String, show>();
        model.put("show1", new show("Breaking Bad", "Ozymandias", 10.0));
        model.put("show2", new show("Attack on Titan", "Hero", 9.9));
        model.put("show3", new show("Attack on Titan", "Perfect Game", 9.9));
        model.put("show4", new show("Star Wars: The Clone Wars", "Victory and Death", 9.9));
        model.put("show5", new show("Mr. Robot", "407 Proxy Authentication Required", 9.9));
        
        return new ModelAndView("shows", model);
    }

}
