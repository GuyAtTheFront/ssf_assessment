package com.iss.nus.assessment.models;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.netty.util.internal.ThreadLocalRandom;
import jakarta.json.Json;

public class Order {

    private String orderId; // order id, string
    private String name; // name, string, view 1
    private String address; // address string, view 1
    private String phone; // phone, string view 1
    private boolean rush; // true or false, boolean, view 1 
    private String comments; // comments, string, view 1
    private String pizza; // pizza name, string, view 0
    private String size;  // pizza size, string, view 0
    private Integer quantity; // quantity, number, view 0
    private Float pizzaCost; 
    private Float totalCost; // total cost, number

    private final Map<String, String> PRICES = Stream.of(new String[][] {
                                                    {"bella", "30"},
                                                    {"marinara", "30"},
                                                    {"spianatacalabrese", "30"},
                                                    {"margherita", "22"},
                                                    {"trioformaggio", "25"}}
                                                    ).collect(Collectors.collectingAndThen(
                                                        Collectors.toMap(data -> data[0], data -> data[1]), 
                                                        Collections::<String, String> unmodifiableMap));


    private final Map<String, String> SIZES = Stream.of(new String[][] {
                                                {"sm", "1"},
                                                {"md", "1.2"},
                                                {"lg", "1.5"}}
                                                ).collect(Collectors.collectingAndThen(
                                                    Collectors.toMap(data -> data[0], data -> data[1]), 
                                                    Collections::<String, String> unmodifiableMap));
    
    
        public void calculateCosts(){
        
        Integer qty = this.getQuantity();
        Float price = Float.parseFloat(PRICES.get(getPizza()));
        Float multiplier = Float.parseFloat(SIZES.get(getSize()));
        Integer addOn = isRush() ? 2 : 0;

        float total = (qty * price * multiplier);
        this.pizzaCost = total;
        this.totalCost = this.pizzaCost + addOn;

        return;
    }

    public String generateId() {    
        long max = Long.parseLong("ffffffff", 16);
            
        long randLong = ThreadLocalRandom.current().nextLong(0, max + 1);
        
        String fname = ("00000000" + Long.toHexString(randLong)).substring(8);

        // TODO: REMOVE LATER, CHECK DUPLICATE
        setOrderId(fname);
        return fname;
    }

    public String toJson() {
        return Json.createObjectBuilder()
                    .add("orderId", getOrderId())
                    .add("name", getName())
                    .add("address", getAddress())
                    .add("phone", getPhone())
                    .add("rush", isRush())
                    .add("comments", getComments())
                    .add("pizza", getPizza())
                    .add("size", getSize())
                    .add("quantity", getQuantity())
                    .add("total", getTotalCost())
                    .build()
                    .toString();
    }
    
    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", name=" + name + ", address=" + address + ", phone=" + phone + ", rush="
                + rush + ", comments=" + comments + ", pizza=" + pizza + ", size=" + size + ", quantity=" + quantity
                + ", totalCost=" + totalCost + "]";
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isRush() {
        return rush;
    }

    public void setRush(boolean rush) {
        this.rush = rush;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotal(Float total) {
        this.totalCost = total;
    }

    public Float getPizzaCost() {
        return pizzaCost;
    }

    public void setPizzaCost(Float pizzaCost) {
        this.pizzaCost = pizzaCost;
    }
    
 
    
}