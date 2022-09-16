package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {
    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("title", "All Employers");
        return "employers/index";

    }
//created the find all method to search through and find all the employers to search through//
    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "employers/add";
        }
        employerRepository.save(newEmployer);
//this is saving the new employer information once it is added back to the data base//
        return "redirect:";
    }

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional  optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employer);
            model.addAttribute("title", "Edit Employer  " + employer.getName() + " (id=" + employer.getId() + ")");
            return "employers/view";
        } else {
            return "redirect:../";
        }
    }



}

