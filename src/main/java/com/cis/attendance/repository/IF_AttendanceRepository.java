package com.cis.attendance.repository;

import com.cis.attendance.dto.AttendanceDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IF_AttendanceRepository {
    public void attendanceInsert(AttendanceDTO attendanceDTO) throws Exception;
}
