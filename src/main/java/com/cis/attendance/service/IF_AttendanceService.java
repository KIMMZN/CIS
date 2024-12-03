package com.cis.attendance.service;

import com.cis.attendance.dto.AttendanceDTO;

public interface IF_AttendanceService {
    public void workStart(AttendanceDTO attendanceDTO) throws Exception;
}
