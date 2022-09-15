package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Skill extends AbstractEntity {
    @NotBlank(message="Please enter a response")
    @Size(min=3, max=300, message="This response must be between 3 and 300 characters. Please try again")
    private String description;

    public Skill(String description) {
        this.description = description;
    }
    public Skill(){

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}