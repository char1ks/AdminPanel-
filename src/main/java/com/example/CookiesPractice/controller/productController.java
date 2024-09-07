package com.example.CookiesPractice.controller;

import com.example.CookiesPractice.model.Person;
import com.example.CookiesPractice.model.Product;
import com.example.CookiesPractice.service.personService;
import com.example.CookiesPractice.service.productService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class productController {
    private productService operations;
    private personService operations2;
    @Autowired
    public void setOperations(productService operations,personService operations2) {
        this.operations = operations;
        this.operations2=operations2;
    }
    //ELEMENTS
    @GetMapping
    public String mainPage(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Product> productPage = operations.getAllProducts(PageRequest.of(page, size));
        model.addAttribute("productPage", productPage);
        return "products/mainPage";
    }
    @GetMapping("/sort")
    public String mainPage2(Model model, @RequestParam(defaultValue = "product_id") String sortBy) {
        List<Product> productList = operations.sortingVariant(sortBy);
        model.addAttribute("productList", productList);
        return "products/sortPage";
    }
    @GetMapping("/{id}")
    public String concretePage(@PathVariable("id") int id, Model model) {
        Product product = operations.getConcreteProduct(id);
        model.addAttribute("concreteProduct", product);
        model.addAttribute("personWhosBought", product.getUser());
        return "products/concretePage";
    }
    //ADD
    @GetMapping("/new")
    public String newPage(@ModelAttribute("newProduct")Product product){
        return "products/newPage";
    }
    @PostMapping
    public String newInDB(@ModelAttribute("newProduct") @Valid Product product,
                          BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "products/newPage";
        operations.saveProduct(product);
        return "redirect:/product";
    }
    //EDIT
    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id")int id,Model model){
        model.addAttribute("concreteProduct",operations.getConcreteProduct(id));
        model.addAttribute("productId",id);
        return "products/editPage";
    }
    @PatchMapping("/{id}")
    public String editInDB(@PathVariable("id")int id,@ModelAttribute("concreteProduct") @Valid Product product,BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "products/editPage";
        operations.updateProduct(id,product);
        return "redirect:/product";
    }
    //DELETE
    @DeleteMapping("/{id}")
    public String deleteInDB(@PathVariable("id")int id){
        operations.deleteProduct(id);
        return "redirect:/product";
    }
    //
    @GetMapping("/select/{personId}")
    public String selectorOfProducts(@PathVariable("personId")int personId,Model model){
        model.addAttribute("personId",personId);
        model.addAttribute("avaliableProductList",operations.getAllWherePersonIdNull());
        return "products/select";
    }
    @PatchMapping("/{productId}/{personId}")
    public String selectEditINDB(@PathVariable("productId")int productId,
                                 @PathVariable("personId")int personId){
        Product product=operations.getConcreteProduct(productId);
        product.setUser(operations2.getConcretePerson(personId));
        operations.updateProduct(productId,product);
        return "redirect:/product/select/"+personId;
    }
}
