package com.cis.attendance.controller;

import com.cis.Pagination;
import com.cis.attendance.dto.AttendanceDTO;
import com.cis.attendance.service.IF_AttendanceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AttendanceController {

    @Autowired
    private IF_AttendanceService attendanceService;

    @GetMapping(value = "attendance")
    public String attendance(@RequestParam(defaultValue = "1") int page, Model model, HttpSession httpSession) throws Exception {
        Object login_emp = httpSession.getAttribute("employee_id");
        if (login_emp == null) return "total_login";

        int totalListCnt = attendanceService.attendanceListCnt(login_emp);
        Pagination pagination = new Pagination(6, totalListCnt, page);

        int startIndex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();
        pagination.setSelectPage(page);

        List<AttendanceDTO> attendance_list = attendanceService.attendanceList(login_emp, startIndex, pageSize);

        model.addAttribute("attendance_list", attendance_list);
        model.addAttribute("pagination", pagination);
        return "attendance/attendance";
    }

    @PostMapping(value = "go_to_work")
    public String workStart(@ModelAttribute AttendanceDTO attendanceDTO, HttpSession httpSession) throws Exception {
        Object login_emp = httpSession.getAttribute("employee_id");
        if (login_emp == null) return "total_login";

        attendanceDTO.setEmp_id((String)login_emp);

        attendanceService.workStart(attendanceDTO);
        return "redirect:attendance";
    }

    @PostMapping(value = "leave_work")
    public String workEnd(@ModelAttribute AttendanceDTO attendanceDTO, HttpSession httpSession) throws Exception {
        Object login_emp = httpSession.getAttribute("employee_id");
        if (login_emp == null) return "total_login";

        attendanceDTO.setEmp_id((String)login_emp);

        if (attendanceDTO.getSignificant().equals("퇴근")) attendanceDTO.setSignificant(null);
        attendanceService.attendanceMod(attendanceDTO);
        return "redirect:attendance";
    }

}
