package com.srp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srp.data.dto.StudentDTO;
import com.srp.data.dto.StudentGroupDTO;
import com.srp.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("api/rest/srp/student")
@RestController
@Slf4j
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/registerStudent")
    public ResponseEntity<StudentDTO> registerStudent(@RequestBody StudentDTO studentDto) {
        log.info("Student to be added : {}", studentDto);

        studentDto = studentService.registerStudent(studentDto);
        log.info("Student added : {}", studentDto);

        if (studentDto != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(studentDto);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }

    @GetMapping("/getStudentForReportGeneration/{userName}")
    public ResponseEntity<List<StudentDTO>> getStudentForReportGeneration(@PathVariable("userName") String userName) {
        // log.info("In getStudentForReportGeneration ()");
        List<StudentDTO> studentDtoList = studentService.getStudentForReportGeneration(userName);
        if (studentDtoList != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(studentDtoList);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }

    @GetMapping("/fetchStudentList/{studentUserName}")
    public ResponseEntity<List<StudentDTO>> fetchStudentForGroupUpdate(@PathVariable("studentUserName") String userName) {

        // log.info("In fetchStudentForGroupUpdate Student Controller {} ", userName);
        List<StudentDTO> studentDtoList = studentService.fetchStudentForGroupUpdate(userName);
        // log.info("studentDto {} ", studentDtoList);

        if (studentDtoList != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(studentDtoList);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }

    @PutMapping("/updateGroupCode")
    public ResponseEntity<StudentDTO> updateGroupCode(@RequestBody StudentDTO studentDto) {
        // log.info("In updateGroupCode ()");
        studentDto = studentService.updateGroupCode(studentDto);
        // log.info("studentDto {} ", studentDto);

        if (studentDto != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(studentDto);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }

    @GetMapping("/loadCountOfStudentYearWise")
    public ResponseEntity<Map<Long, Long>> loadCountOfStudentYearWise() {
        // log.info("In loadCountOfStudentYearWise ()");
        Map<Long, Long> loadCountOfStudentYearWiseMap = studentService.loadCountOfStudentYearWise();

        if (loadCountOfStudentYearWiseMap != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(loadCountOfStudentYearWiseMap);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }

    @GetMapping("/fetchYearWiseStudentRecords/{year}")
    public ResponseEntity<List<StudentDTO>> fetchYearWiseStudentRecords(@PathVariable("year") Long year) {
        // log.info("In fetchYearWiseStudentRecords ()");
        List<StudentDTO> studentDtoList = studentService.fetchYearWiseStudentRecords(year);

        if (studentDtoList != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(studentDtoList);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }

    @PutMapping("/updateBatchGroupCode")
    public ResponseEntity<List<StudentDTO>> updateBatchGroupCode(@RequestBody List<StudentGroupDTO> studentGroupDto) {
        // log.info("In updateGroupCode ()");
        List<StudentDTO> studentDto = studentService.updateBatchGroupCode(studentGroupDto);
        // log.info("studentDto {} ", studentDto);

        if (studentDto != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(studentDto);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }

    @GetMapping("/findIfUserNameExist/{userName}")
    public ResponseEntity<Boolean> findIfUserNameExist(@PathVariable("userName") String userName) {
        // log.info("in check user name ()");
        boolean result = studentService.checkUserName(userName);
        // log.info("studentDto {} ", result);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/fetchStudentForVerification/{userName}")
    public ResponseEntity<StudentDTO> fetchStudentForVerification(@PathVariable("userName") String userName) {
        // log.info("In fetchStudentForVerification ()");
        StudentDTO studentDto = null;
        studentDto = studentService.fetchStudentForVerification(userName);
        // log.info("studentDto {} ", studentDto);

        if (studentDto != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(studentDto);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }
}
