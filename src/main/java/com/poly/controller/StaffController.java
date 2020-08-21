package com.poly.controller;

import com.poly.model.Depart;

import com.poly.model.Staff;
import com.poly.service.DepartService;
import com.poly.service.RecordService;
import com.poly.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
@RequestMapping("/staffs")
public class StaffController {
    private DepartService departService;
    private StaffService staffService;
    private RecordService recordService;

    @Autowired
    public StaffController(DepartService departService, StaffService staffService, RecordService recordService) {
        this.departService = departService;
        this.staffService = staffService;
        this.recordService = recordService;
    }

    //Sai =))
    @GetMapping("")
    public ModelAndView listStaffs(@RequestParam("string") Optional<String> s, Pageable pageable) {
        Page<Staff> staffs;
        if (s.isPresent()) {
            staffs = staffService.findAllByNameContains(s.get(), pageable);
        } else {
            staffs = staffService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/staff/list");
        modelAndView.addObject("staffs", staffs);
        return modelAndView;
    }


    @ModelAttribute("departs")
    public Iterable<Depart> getAllDepart() {
        Iterable<Depart> departs = departService.findAll();
        return (Iterable<Depart>) departs;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/staff/create");
        modelAndView.addObject("staff", new Staff());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createStaff(@ModelAttribute("staff") Staff staff) {

        ModelAndView modelAndView = new ModelAndView("/staff/create");

        staffService.save(staff);
        modelAndView.addObject("message", "New staff is created");
        modelAndView.addObject("staff", new Staff());
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {

        Optional<Staff> staff = staffService.findById(id);
        if (staff == null) {
            return new ModelAndView("/error404");
        } else {
            ModelAndView modelAndView = new ModelAndView("/staff/edit");
            modelAndView.addObject("staff", staff);
            return modelAndView;
        }
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updateStaff(@ModelAttribute("staff") Staff staff) {
        ModelAndView modelAndView = new ModelAndView("/staff/edit");
        staffService.save(staff);
        modelAndView.addObject("staff", staff);
        modelAndView.addObject("message", "Staff is updated");
        return modelAndView;
    }


    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id) {
        Optional<Staff> staff = staffService.findById(id);
        ModelAndView modelAndView;
        if (staff != null) {
            modelAndView = new ModelAndView("/staff/delete");
            modelAndView.addObject("staff", staff);
        } else {
            modelAndView = new ModelAndView("/error404");
        }
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteStaff(@PathVariable("id") Long id) {
        ModelAndView modelAndView;
        Optional<Staff> staff = staffService.findById(id);
        if (staff != null) {
            staffService.delete(id);
            modelAndView = new ModelAndView("redirect:/staffs");
        } else {
            modelAndView = new ModelAndView("/error404");
        }

        return modelAndView;
    }
}
