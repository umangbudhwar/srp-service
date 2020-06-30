package com.srp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.srp.data.dto.StudentDTO;
import com.srp.data.dto.StudentGroupDTO;
import com.srp.data.entity.Student;

@Service
public interface StudentService extends BaseService<StudentDTO, Student, String> {

    StudentDTO registerStudent(StudentDTO newUser);

    List<StudentDTO> getStudentForReportGeneration(String userName);

    List<StudentDTO> fetchStudentForGroupUpdate(String userName);

    StudentDTO updateGroupCode(StudentDTO studentDto);

    Map<Long,Long> loadCountOfStudentYearWise();

    List<StudentDTO> fetchYearWiseStudentRecords(Long year);

    List<StudentDTO> updateBatchGroupCode(List<StudentGroupDTO> studentGroupDto);

    boolean checkUserName(String userName);

    StudentDTO fetchStudentForVerification(String userName);

}
