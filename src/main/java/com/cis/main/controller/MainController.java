package com.cis.main.controller;

import com.cis.attendance.dto.AttendanceDTO;
import com.cis.attendance.service.IF_AttendanceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private IF_AttendanceService attendanceService;

    @GetMapping(value = "emp_main")
    public String empMain(Model model, HttpSession httpSession) throws Exception {
        Object login_emp = httpSession.getAttribute("employee_id");
        if (login_emp == null) return "total_login";

        List<AttendanceDTO> attendance_list = attendanceService.attendanceList(login_emp, 0, 3);
        model.addAttribute("attendance_list", attendance_list);
        return "main/emp_main";
    }

    @GetMapping(value = "manager_main")
    public String managerMain() {
        return "main/manager_main";
    }

}
