package com.srp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srp.configuration.jpa.SrpRepository;
import com.srp.data.dto.SubjectDTO;
import com.srp.data.entity.Subject;
import com.srp.data.repository.SubjectRepository;
import com.srp.service.SubjectService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SubjectServiceImpl extends BaseServiceImpl<SubjectDTO, Subject, Integer> implements SubjectService {

	@Autowired
	SubjectRepository subjectRepository;
	
	public SubjectServiceImpl(SrpRepository<Subject, Integer> repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<SubjectDTO> getSubjects() {
		log.info("In Subject Service: getSubjects()");
		List<SubjectDTO> subjectDtoList = null;

		try {

			List<Subject> subjectList = subjectRepository.findAll();
			subjectDtoList = getMapper().mapAsList(subjectList, SubjectDTO.class);

		} catch (Exception e) {

			log.error("Exception in Stream Service: getStreams {}", e.getMessage());
		}

		return subjectDtoList;
	}

	
}
