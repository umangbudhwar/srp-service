package com.srp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.srp.data.dto.StreamsDTO;
import com.srp.data.entity.Streams;


@Service
public interface StreamsService extends BaseService<StreamsDTO, Streams, Long> {

	List<StreamsDTO> getStreams();

	Streams findById(Long id);

	//Long getStreamIdFromStreamName(String streamName);

}
