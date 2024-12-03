package com.cis.attendance.service;

import com.cis.attendance.dto.AttendanceDTO;
import com.cis.attendance.repository.IF_AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService implements IF_AttendanceService {

    @Autowired
    IF_AttendanceRepository attendanceRepository;

    @Override
    public void workStart(AttendanceDTO attendanceDTO) throws Exception {
        attendanceRepository.attendanceInsert(attendanceDTO);
    }

}
