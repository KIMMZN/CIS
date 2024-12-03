package com.cis.attendance.controller;

import com.cis.attendance.dto.AttendanceDTO;
import com.cis.attendance.service.IF_AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AttendanceController {

    @Autowired
    private IF_AttendanceService attendanceService;

    @GetMapping(value = "attendance")
    public String attendance(Model model) throws Exception {
        List<AttendanceDTO> attendance_list = attendanceService.attendanceList();
        model.addAttribute("attendance_list", attendance_list);
        return "attendance/attendance";
    }

    @PostMapping(value = "go_to_work")
    public String workStart(@ModelAttribute AttendanceDTO attendanceDTO) throws Exception {
        attendanceService.workStart(attendanceDTO);
        return "redirect:attendance";
    }
}
