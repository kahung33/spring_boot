package com.ltp.globalsuperstore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class StoreController {
    private List<Item> items = new ArrayList<Item>();
    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id){
        model.addAttribute("categories", Constants.CATEGORIES);
        int index = getItemIndex(id);
        if (index != Constants.NOT_FOUND){
            model.addAttribute("item", items.get(index));
        }else{
            model.addAttribute("item", new Item());
        }
        System.out.println("id: " + id);
        return "form";
    }

    @GetMapping("/inventory")
    public String getInventory(Model model){
        model.addAttribute("items", items);
        return "inventory";
    }

    @PostMapping("/submitItem")
    public String handelSubmit(Item item, RedirectAttributes redirectAttributes) {
        int index = getItemIndex(item.getId());
        String status = Constants.SUCCESS_STATUS;
        if(index == Constants.NOT_FOUND){
            items.add(item);
        }else if (within5Days(item.getDate(), items.get(index).getDate())) {
            items.set(index, item);
        }else{
            status = Constants.FAILED_STATUS;
        }
        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/inventory";
    }

    public int getItemIndex(String id){
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getId().equals(id)){
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    public boolean within5Days(Date newDate, Date oldDate) {
        long diff = Math.abs(newDate.getTime() - oldDate.getTime());
        return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 5;
    }

    
}
