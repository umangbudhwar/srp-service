package com.srp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.srp.data.dto.SubjectDTO;
import com.srp.data.entity.Subject;

@Service
public interface SubjectService extends BaseService<SubjectDTO, Subject, Long> {

	List<SubjectDTO> getSubjects();

}
