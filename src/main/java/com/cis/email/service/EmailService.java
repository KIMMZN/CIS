package com.cis.email.service;

import com.cis.email.dto.EmailDTO;
import com.cis.email.dto.EmailFileDTO;
import com.cis.email.repository.IF_EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class EmailService implements IF_EmailService {

    @Autowired
    IF_EmailRepository emailrepository;

    @Override
    public void emailInsert(EmailDTO emailDTO) throws Exception {
        emailrepository.emailInsert(emailDTO);
    }

    @Override
    public List<EmailDTO> emailList(Object login_emp, int startIndex, int pageSize, String filter) throws Exception {
        return switch (filter) {
            case "unread" -> emailrepository.emailSelectCheckAll(login_emp, startIndex, pageSize, "N");
            case "read" -> emailrepository.emailSelectCheckAll(login_emp, startIndex, pageSize, "Y");
            default -> emailrepository.emailSelectAll(login_emp, startIndex, pageSize);
        };
    }

    @Override
    public EmailDTO emailOne(Object login_emp, String email_num) throws Exception {
        return emailrepository.emailSelectOne(login_emp, email_num);
    }

    @Override
    public String emailOrderOne(Object login_emp) throws Exception {
        return emailrepository.emailSelectOrderOne(login_emp);
    }

    @Override
    public int emailListCnt(Object login_emp, String filter) throws Exception {
        return switch (filter) {
            case "unread" -> emailrepository.emailSelectCheckAllCnt(login_emp, "N");
            case "read" -> emailrepository.emailSelectCheckAllCnt(login_emp, "Y");
            default -> emailrepository.emailSelectAllCnt(login_emp);
        };
    }

    @Override
    public void emailCheckUpdate(Object login_emp, String email_num) throws Exception {
        emailrepository.emailUpdate(login_emp, email_num);
    }

    @Override
    public void emailDelete(Object login_emp, String email_num) throws Exception {
        emailrepository.emailDelete(login_emp, email_num);
    }

    @Override
    public void emailFileUpload(final String mail_num, final List<EmailFileDTO> email_files) throws Exception {
        if (CollectionUtils.isEmpty(email_files)) return;
        for (EmailFileDTO email_file : email_files) {
            email_file.setMail_num(mail_num);
        }
        emailrepository.emailFileInsert(email_files);
    }

    @Override
    public List<EmailFileDTO> emailNumFind(String mail_num) throws Exception {
        return emailrepository.emailNumFind(mail_num);
    }

    @Override
    public List<EmailFileDTO> emailFileNameFind(List<String> file_name) throws Exception {
        if (CollectionUtils.isEmpty(file_name)) return Collections.emptyList();
        return emailrepository.emailFileNameFind(file_name);
    }

    @Override
    public EmailFileDTO emailFileFind(String file_name) throws Exception {
        return emailrepository.emailFileFind(file_name);
    }

    @Override
    public String recipientIdCheck(String recipient_id) throws Exception {
        return emailrepository.findEmployeeId(recipient_id);
    }

}
