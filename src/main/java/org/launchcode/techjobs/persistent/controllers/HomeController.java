package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.events.Event;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;


    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute(new Job());
        model.addAttribute("skills", skillRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }
        //setting a variable call opt employer to be the return value of find by id which will be an optional//
        Optional<Employer>  optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {//checking to see if that id is present in the data base//
            Employer employer = optEmployer.get();//if it is present then we are setting a new variable called employer to be the value of the object found. //
            newJob.setEmployer(employer); // set the new employer object//
        }
        List<Skill> newSkills = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setSkills(newSkills);

            jobRepository.save(newJob);

            return "redirect:";
        }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Optional<Job> optionalJob = jobRepository.findById(jobId);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            model.addAttribute("job", job);
            return "view";
        } else {
            return "redirect:";
        }


    }


}
