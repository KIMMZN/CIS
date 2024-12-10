package com.cis.email.controller;

import com.cis.email.component.EmailFileUtils;
import com.cis.Pagination;
import com.cis.email.dto.EmailDTO;
import com.cis.email.dto.EmailFileDTO;
import com.cis.email.service.IF_EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmailController {

    @Autowired
    IF_EmailService emailservice;
    @Autowired
    private EmailFileUtils emailFileUtils;

    @GetMapping(value = "email")
    public String email(@RequestParam(value = "filter", defaultValue = "all") String filter, @RequestParam(defaultValue = "1") int page, Model model, HttpSession httpSession) throws Exception {
        Object login_emp = httpSession.getAttribute("employee_id");
        if (login_emp == null) return "total_login";

        int totalListCnt = emailservice.emailListCnt(login_emp, filter);
        Pagination pagination = new Pagination(6, totalListCnt, page);

        int startIndex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();
        pagination.setSelectPage(page);
        pagination.setFilter(filter);

        List<EmailDTO> email_list = emailservice.emailList(login_emp, startIndex, pageSize, filter);

        model.addAttribute("email_list", email_list);
        model.addAttribute("pagination", pagination);
        return "email/mail";
    }

    @PostMapping(value = "email_delete")
    public String emailDelete(@RequestParam("num") List<String> email_num_list, HttpSession httpSession) throws Exception {
        Object login_emp = httpSession.getAttribute("employee_id");
        if (login_emp == null) return "total_login";

        for (String email_num : email_num_list) {
            List<EmailFileDTO> file_name_list = emailservice.emailNumFind(email_num);
            emailFileUtils.deleteFiles(file_name_list);
            emailservice.emailDelete(login_emp, email_num);
        }
        return "redirect:email";
    }

    @GetMapping(value = "email_send")
    public String mailSend(HttpSession httpSession) {
        if ((httpSession.getAttribute("employee_id")) == null) return "total_login";
        return "email/mail_send";
    }

    @PostMapping(value = "email_insert")
    public String mailInsert(@ModelAttribute EmailDTO emaildto, HttpSession httpSession) throws Exception {
        Object login_emp = httpSession.getAttribute("employee_id");
        if (login_emp == null) return "total_login";

        emaildto.setSender_id((String)login_emp);
        emailservice.emailInsert(emaildto);

        String mail_num = emailservice.emailOrderOne(login_emp);
        List<EmailFileDTO> email_files = emailFileUtils.uploadFiles(emaildto.getMail_files());
        emailservice.emailFileUpload(mail_num, email_files);
        return "redirect:email";
    }

    @PostMapping(value = "recipient_id/check")
    @ResponseBody
    public int recipientIdCheck(@RequestParam("recipient_id") String recipient_id) throws Exception {
        // 아이디가 있을 경우(0)
        if (recipient_id.equals(emailservice.recipientIdCheck(recipient_id))) return 0;
        // 아이디가 없을 경우(1)
        return 1;
    }

    @GetMapping(value = "email_detail")
    public String mailDetail(@RequestParam("num") String email_num, Model model, HttpSession httpSession) throws Exception {
        Object login_emp = httpSession.getAttribute("employee_id");
        if (login_emp == null) return "total_login";

        EmailDTO email_one = emailservice.emailOne(login_emp, email_num);
        List<EmailFileDTO> email_files = emailservice.emailNumFind(email_num);
        if (email_one.getMail_check().equals("N")) emailservice.emailCheckUpdate(login_emp, email_num);
        model.addAttribute("email_files", email_files);
        model.addAttribute("email_one", email_one);
        return "email/mail_detail";
    }

}
