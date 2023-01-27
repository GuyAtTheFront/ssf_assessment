package com.iss.nus.assessment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.iss.nus.assessment.models.Confirm;
import com.iss.nus.assessment.models.Pizza;
import com.iss.nus.assessment.models.Order;

import com.iss.nus.assessment.repositories.RedisRepo;

import jakarta.validation.Valid;

@Controller
public class PizzaController {
    
    @Autowired
    RedisRepo redis;

    private Order order;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "index";
    }

    @PostMapping("/pizza")
    public String confirm(@Valid Pizza pizza, BindingResult bindingResult, Model model) {
        
        // Custom validation --> Add to bindingResult before check
        pizza.validatePizzas(bindingResult);
        pizza.validateSizes(bindingResult);
        
        if(bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult);
            return "index";
        }

        order = new Order();
        order.setPizza(pizza.getPizza());
        order.setQuantity(pizza.getQuantity());
        order.setSize(pizza.getSize());

        model.addAttribute("confirm", new Confirm());
        return "confirm";
    }

    @PostMapping("/pizza/order")
    public String order(@Valid Confirm confirm, 
                        BindingResult bindingResult,
                        Model model) {

    if(bindingResult.hasErrors()) {
        model.addAttribute("errors", bindingResult);
        return "confirm";
    }
        
        order.setAddress(confirm.getAddress());
        order.setComments(confirm.getComments());
        order.setName(confirm.getName());
        order.setPhone(confirm.getPhone());
        order.setRush(confirm.isRush());
        
        order.generateId();
        order.calculateCosts();

        redis.insertRecord(order.getOrderId(), order.toJson());

        model.addAttribute("order", order);
        return "order";
    }
}
