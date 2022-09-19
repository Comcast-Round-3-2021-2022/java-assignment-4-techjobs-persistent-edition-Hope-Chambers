package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {
    @NotBlank(message="Please enter a response")
    @Size(min=3, max=300, message="This response must be between 3 and 300 characters. Please try again")
    private String description;

    @ManyToMany(mappedBy = "skills")
    private List<Job> jobs  = new ArrayList<>();
//Jobs each own id and so do the skills. establishing the relation between the //
// skills ids and the jobs basically going to see a join table one is jobs one is skills//
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

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
