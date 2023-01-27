package com.iss.nus.assessment.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Confirm {

    @NotNull(message="Name cannot be null")
    @NotEmpty(message="Name cannot be empty")
    @Size(min = 3, message="Name must be at least 3 characters long")
    private String name;

    @NotNull(message="Address cannot be null")
    @NotEmpty(message="Address cannot be empty")
    private String address;

    @NotNull(message="Number cannot be null")
    @NotEmpty(message="Number cannot be empty")
    @Pattern(regexp = "^[()+*#-]*(?:\\d[()+*#-]*){8,}$", message="Number must contain at least 8 digits")
    private String phone;
    private boolean rush = false;
    private String comments;
    
    @Override
    public String toString() {
        return "Confirm [name=" + name + ", address=" + address + ", phone=" + phone + ", rush=" + rush + ", comments="
                + comments + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone.trim();
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
        if(comments.isBlank() || comments.isEmpty() || comments==null) {
            comments = "NIL";
        }        
        this.comments = comments;
    }



    
}
