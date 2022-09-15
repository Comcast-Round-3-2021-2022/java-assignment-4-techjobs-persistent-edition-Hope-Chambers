package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank(message="Please enter a response")
    @Size(min=3, max=250, message="This response must be between 3 and 250 characters. Please try again")
    private String location;

    public Employer(String location) {
        this.location = location;
    }

    public Employer(){

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }




}

