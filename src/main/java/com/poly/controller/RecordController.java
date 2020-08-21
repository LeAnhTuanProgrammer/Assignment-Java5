package com.poly.controller;

import com.poly.model.Depart;
import com.poly.model.Record;
import com.poly.model.Staff;
import com.poly.service.RecordService;
import com.poly.service.StaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/records")
public class RecordController {
    private StaffService staffService;
    private RecordService recordService;

    @Autowired
    public RecordController(StaffService staffService, RecordService recordService) {
        this.staffService = staffService;
        this.recordService = recordService;
    }
    @GetMapping("")
    public ModelAndView listRecords(@RequestParam("string") Optional<String> s, Pageable pageable) {
        Page<Record> records;
        if (s.isPresent()) {
            records = recordService.findAllByReasonContains(s.get(), pageable);
        } else {
            records = recordService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/record/list");
        modelAndView.addObject("records", records);
        return modelAndView;
    }



    @ModelAttribute("staffs")

    public Page<Staff> getAllStaff(Pageable pageable) {

        Page<Staff> staffs = staffService.findAll(pageable);
        return staffs;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/record/create");
        modelAndView.addObject("record", new Record());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createRecord(@ModelAttribute("record") Record record) {
        ModelAndView modelAndView = new ModelAndView("/record/create");
        recordService.save(record);
        modelAndView.addObject("message", "New record is created");
        modelAndView.addObject("record", new Record());

        return modelAndView;
    }



    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        Optional<Record> record = recordService.findById(id);
        if (record == null) {
            return new ModelAndView("/error404");
        } else {
            ModelAndView modelAndView = new ModelAndView("/record/edit");
            modelAndView.addObject("record", record);
            return modelAndView;
        }
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updateRecord(@ModelAttribute("record") Record record) {
        ModelAndView modelAndView = new ModelAndView("/record/edit");
        recordService.save(record);
        modelAndView.addObject("record", record);
        modelAndView.addObject("message", "Record is updated");
        return modelAndView;
    }


    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id) {
        Optional<Record> record = recordService.findById(id);
        ModelAndView modelAndView;
        if (record != null) {
            modelAndView = new ModelAndView("/record/delete");
            modelAndView.addObject("record", record);
        } else {
            modelAndView = new ModelAndView("/error404");
        }
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteTest(@PathVariable("id") Long id) {
        ModelAndView modelAndView;
        Optional<Record> record = recordService.findById(id);
        if (record != null) {
            recordService.delete(id);
            modelAndView = new ModelAndView("redirect:/records");
        } else {
            modelAndView = new ModelAndView("/error404");
        }

        return modelAndView;
    }
}
