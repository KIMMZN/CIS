package com.cis.attendance.service;

import com.cis.attendance.dto.AttendanceDTO;

import java.util.List;

public interface IF_AttendanceService {
    public void workStart(AttendanceDTO attendanceDTO) throws Exception;
    public List<AttendanceDTO> attendanceList() throws Exception;
}
