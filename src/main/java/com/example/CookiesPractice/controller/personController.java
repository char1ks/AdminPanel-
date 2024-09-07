package com.example.CookiesPractice.controller;

import com.example.CookiesPractice.model.Person;
import com.example.CookiesPractice.service.personService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class personController {
    private personService operations;

    @Autowired
    public void setOperations(personService operations) {
        this.operations = operations;
    }
    //ELEMENTS
    @GetMapping
    public String mainPage(Model model){
        model.addAttribute("allPerson",operations.getAllPeople());
        return "people/mainPage";
    }
    @GetMapping("/{id}")
    public String concretePage(@PathVariable("id")int id,
                               Model model){
        model.addAttribute("concretePerson",operations.getConcretePerson(id));
        return "people/concretePage";
    }
    //ADD
    @GetMapping("/new")
    public String newPage(@ModelAttribute("newPerson") Person person){
        return "people/newPage";
    }
    @PostMapping
    public String newInDB(@ModelAttribute("newPerson")@Valid Person person,
                          BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "people/newPage";
        operations.savePerson(person);
        return "redirect:/product/select/"+person.getId();
    }
    //EDIT
    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id")int id,Model model){
        model.addAttribute("editPerson",operations.getConcretePerson(id));
        model.addAttribute("personId",id);
        return "people/editPage";
    }
    @PatchMapping("/{id}")
    public String editInDB(@PathVariable("id")int id,
                           @ModelAttribute("editPerson")@Valid Person person,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "people/editPage";
        operations.updatePerson(id,person);
        return "redirect:/people";
    }
    //DELETE
    @DeleteMapping("/{id}")
    public String deleteInDB(@PathVariable("id")int id){
        operations.deletePerson(id);
        return "redirect:/people";
    }
}
