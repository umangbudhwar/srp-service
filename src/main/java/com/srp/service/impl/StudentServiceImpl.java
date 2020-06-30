package com.srp.service.impl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.srp.configuration.jpa.SrpRepository;
import com.srp.data.dto.StudentDTO;
import com.srp.data.dto.StudentGroupDTO;
import com.srp.data.dto.SubjectDTO;
import com.srp.data.entity.Faculty;
import com.srp.data.entity.Streams;
import com.srp.data.entity.Student;
import com.srp.data.entity.User;
import com.srp.data.repository.StudentRepository;
import com.srp.data.repository.UserRepository;
import com.srp.service.FacultyService;
import com.srp.service.StreamsService;
import com.srp.service.StudentService;
import com.srp.service.SubjectService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentServiceImpl extends BaseServiceImpl<StudentDTO, Student, String> implements StudentService {

    public StudentServiceImpl(SrpRepository<Student, String> repository) {
        super(repository);
        // TODO Auto-generated constructor stub
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FacultyService facultyService;

    @Autowired
    StreamsService streamService;

    @Autowired
    SubjectService subjectService;

    @Override
    public StudentDTO registerStudent(StudentDTO newUser) {
        // log.info("In Student service Impl");

        Student student = null;
        int firstNameUniqueCount = 0;
        Integer yearId = (LocalDate.now()
            .getYear()) % 100;

        try {
            student = studentRepository.findById(newUser.getUserName()).get();
            if (student != null) {
                student = getMapper().map(newUser, Student.class);
                student.setVerified(true);
                student = studentRepository.save(student);
            } else {
                if (newUser.getStudentOTP() == 456789) {
                    student = getMapper().map(newUser, Student.class);
                    student.setPassword(passwordEncoder.encode(student.getPassword()));
                    firstNameUniqueCount = studentRepository.countByFirstName(student.getFirstName());
                    String studentCodeFirstName = newUser.getFirstName()
                        .toUpperCase();
                    String studentCodeFatherName = newUser.getFatherName()
                        .toUpperCase();

                    if (firstNameUniqueCount <= 1) {

                        // setting student code without Father's name
                        student.setStudentCode(yearId + studentCodeFirstName + newUser.getCollegeYear());
                    } else {
                        // setting student code with Father's name
                        student.setStudentCode(yearId + studentCodeFirstName + studentCodeFatherName + newUser.getCollegeYear());
                    }

                    student = studentRepository.save(student);

                    // log.info("User saved in Student.");

                    User user = getMapper().map(newUser, User.class);
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    user.setRole("ROLE_STUDENT");
                    user = userRepository.save(user);
                    // log.info("User saved in User table.");
                }
            }

        } catch (Exception e) {

            // log.error("Exception in Student Service Impl: saveUser(): {} ", e.getMessage());
            student = null;
        }
        return getMapper().map(student, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> getStudentForReportGeneration(String userName) {

        // log.info("In getStudentForReportGeneration Service Impl:");

        Faculty facultyObj = null;
        List<Student> studentList = null;
        List<StudentDTO> studentDTOList = null;
        List<SubjectDTO> subjectList = subjectService.findAll();

        try {

            facultyObj = facultyService.findByUserName(userName);
            // log.info("after getting faculty object {}: ", facultyObj);
            studentList = studentRepository.findByStreamId(facultyObj.getStreamId());
            studentDTOList = getMapper().mapAsList(studentList, StudentDTO.class);

            studentDTOList.stream()
                .forEach(studentDto -> {
                    Streams stream = streamService.findById(studentDto.getStreamId());
                    studentDto.setStreamName(stream.getStreamName());
                    List<Long> subjectIdList = studentDto.getSubjectId();// Arrays.asList(studentDto.getSubjectId());
                    List<String> subjectNameList = subjectList.stream()
                        .filter(sub -> subjectIdList.stream()
                            .filter(subId -> sub.getSubjectId()
                                .equals(subId))
                            .count() >= 1)
                        .map(SubjectDTO::getSubjectName)
                        .collect(Collectors.toList());
                    studentDto.setSubjectName(subjectNameList);
                    studentDto.setUserName(null);
                    studentDto.setPassword(null);
                });

            studentDTOList.stream()
                .filter(student -> student.isVerified())
                .collect(Collectors.toList());

            // log.info("{}", studentDTOList);
        } catch (Exception e) {
            // log.error("Exception in getStudentForReportGeneration Service Impl: {} ", e.getMessage());
            e.printStackTrace();
            studentDTOList = null;
        }

        return studentDTOList;
    }

    @Override
    public List<StudentDTO> fetchStudentForGroupUpdate(String userName) {

        // log.info("In fetchStudentForGroupUpdate Service Impl:");
        List<Student> studentList = null;

        try {
            studentList = studentRepository.findAllStudentsByUserNameOrFirstNameOrLastNameOrEmail(userName);
            studentList.stream()
                .filter(student -> student.isVerified())
                .collect(Collectors.toList());
            // log.info("student object {} ", studentList);

        } catch (Exception e) {
            // log.error("Exception in fetchStudentForGroupUpdate Service Impl: {} ", e.getMessage());
            e.printStackTrace();
            studentList = null;
        }
        return getMapper().mapAsList(studentList, StudentDTO.class);
        // return studentDtoList;
    }

    @Override
    public StudentDTO updateGroupCode(StudentDTO studentDto) {
        // log.info("In updateGroupCode Service Impl:");
        Student student = null;
        int firstNameUniqueCount = 0;
        Integer yearId = (LocalDate.now()
            .getYear()) % 100;
        try {
            student = getMapper().map(studentDto, Student.class);
            firstNameUniqueCount = studentRepository.countByFirstName(studentDto.getFirstName());
            String studentCodeFirstName = studentDto.getFirstName()
                .toUpperCase();
            String studentCodeFatherName = studentDto.getFatherName()
                .toUpperCase();

            if (firstNameUniqueCount <= 1) {

                // setting student code without Father's name
                student.setStudentCode(yearId + studentCodeFirstName + studentDto.getCollegeYear() + studentDto.getGroupDivision());
            } else {
                // setting student code with Father's name
                student.setStudentCode(yearId + studentCodeFirstName + studentCodeFatherName + studentDto.getCollegeYear() + studentDto.getGroupDivision());
            }

            student = studentRepository.save(student);

        } catch (Exception e) {
            // log.error("Exception in updateGroupCode Service Impl: {} ", e.getMessage());
            e.printStackTrace();
            student = null;
        }

        return getMapper().map(student, StudentDTO.class);
    }

    @Override
    public Map<Long, Long> loadCountOfStudentYearWise() {
        // log.info("In loadCountOfStudentYearWise Service Impl:");
        Map<Long, Long> loadCountOfStudentYearWiseMap = new HashMap<>();
        Object[][] studentDtoObj = null;

        try {
            studentDtoObj = studentRepository.countByYearAndUserName();

            for (Object[] object : studentDtoObj) {
                loadCountOfStudentYearWiseMap.put(((BigInteger) object[0]).longValue(), ((BigInteger) object[1]).longValue());
            }
            // log.info("loadCountOfStudentYearWiseMap {}", loadCountOfStudentYearWiseMap);

        } catch (Exception e) {
            // log.error("Exception in loadCountOfStudentYearWise Service Impl: {} ", e.getMessage());
            e.printStackTrace();
            loadCountOfStudentYearWiseMap = null;
        }

        return loadCountOfStudentYearWiseMap;
    }

    @Override
    public List<StudentDTO> fetchYearWiseStudentRecords(Long year) {
        // log.info("In fetchYearWiseStudentRecords Service Impl:");
        List<Student> studentList = null;
        try {

            studentList = studentRepository.findByCollegeYear(year);
            studentList.stream()
                .filter(student -> student.isVerified())
                .collect(Collectors.toList());
            // log.info("fetchYearWiseStudentRecords list size {}", studentList.size());
        } catch (Exception e) {
            // log.error("Exception in fetchYearWiseStudentRecords Service Impl: {} ", e.getMessage());
            e.printStackTrace();
            studentList = null;
        }

        return getMapper().mapAsList(studentList, StudentDTO.class);
    }

    @Override
    @Transactional
    public List<StudentDTO> updateBatchGroupCode(List<StudentGroupDTO> studentGroupDto) {
        // log.info("In updateBatchGroupCode Service Impl:");
        List<Student> studentList = new ArrayList<>();
        String userName = "";
        String groupDivision = "";
        Student studentObj = null;
        int rowUpdated = 0;
        try {
            for (StudentGroupDTO student : studentGroupDto) {
                userName = student.getUserName();
                groupDivision = student.getGroupNumber();
                rowUpdated = studentRepository.updateBatchGroupCode(userName, groupDivision);
                // log.info("student group updated : {}", rowUpdated);
                if (rowUpdated == 1) {
                    studentObj = studentRepository.findById(userName)
                        .get();
                    studentList.add(studentObj);
                }
                /* else {
                   studentObj = studentRepository.findById(userName).get();
                   studentList.add(studentObj);   
                }*/
            }

        } catch (Exception e) {
            // log.error("Exception in updateBatchGroupCode Service Impl: {} ", e.getMessage());
            e.printStackTrace();
            studentList = null;
        }

        return getMapper().mapAsList(studentList, StudentDTO.class);
    }

    @Override
    public boolean checkUserName(String userName) {

        // log.info("in check username {} " , userName);
        boolean userExistFlag = false;

        try {
            userExistFlag = userRepository.existsUserByUserNameIgnoreCase(userName);
        } catch (Exception e) {
            // log.error("Exception in checkUserName Service Impl: {} ", e.getMessage());
            e.printStackTrace();
            userExistFlag = false;
        }
        return userExistFlag;
    }

    @Override
    public StudentDTO fetchStudentForVerification(String userName) {

        Student student = null;

        try {
            student = studentRepository.findById(userName)
                .get();
        } catch (Exception e) {
            // log.error("Exception in checkUserName Service Impl: {} ", e.getMessage());
            e.printStackTrace();
            student = null;
        }
        return getMapper().map(student, StudentDTO.class);
    }

}
