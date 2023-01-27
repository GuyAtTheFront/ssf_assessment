package com.iss.nus.assessment.models;

import java.util.Arrays;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Pizza {

    @NotNull(message = "Pizza not select")
    private String pizza;
    
    @NotNull (message = "Size not selected")
    private String size;
    
    @NotNull(message = "Quantity cannot be null")
    @Min(value=1, message = "Quantity min = 1")
    @Max(value=10, message = "Quantity max = 10")
    private Integer quantity;


    private final String[] PIZZAS = {"margherita", 
                                    "marinara", 
                                    "bella", 
                                    "spianatacalabrese", 
                                    "trioformaggio" };

    private final String[] SIZES = {"sm", "md", "lg"};

    public void validatePizzas(BindingResult result) {
        
        if(!Arrays.asList(PIZZAS).contains(getPizza())) {
            FieldError err = new FieldError("pizza", "pizza", "Invalid Pizza Selection");
            result.addError(err);
        }

        return;
    }

    public void validateSizes(BindingResult result) {

        if(!Arrays.asList(SIZES).contains(getSize())) {
            FieldError err = new FieldError("size", "size", "Invalid Size");
            result.addError(err);
        }
        return;
    }
    

    public String getPizza() {
        return pizza;
    }
    public void setPizza(String pizza) {
        this.pizza = pizza;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
    @Override
    public String toString() {
        return "Pizza [pizza=" + pizza + ", size=" + size + ", quantity=" + quantity + "]";
    }
    
    
    
}
