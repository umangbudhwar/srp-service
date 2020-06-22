package com.srp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srp.configuration.jpa.SrpRepository;
import com.srp.data.dto.StreamsDTO;
import com.srp.data.entity.Streams;
import com.srp.data.repository.StreamsRepository;
import com.srp.service.StreamsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StreamsServiceImpl extends BaseServiceImpl<StreamsDTO, Streams, Long> implements StreamsService {

	@Autowired
	StreamsRepository streamRepository;

	public StreamsServiceImpl(SrpRepository<Streams, Long> repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<StreamsDTO> getStreams() {

		// log.info("In Stream Service: getStreams()");
		List<StreamsDTO> streamDtoList = null;

		try {

			List<Streams> streamList = streamRepository.findAll();
			streamDtoList = getMapper().mapAsList(streamList, StreamsDTO.class);

		} catch (Exception e) {

			// log.error("Exception in Stream Service: getStreams {}", e.getMessage());
		}

		return streamDtoList;
	}

	@Override
	public Streams findById(Long streamId) {
		
		Streams stream = null;
		try {
			
			stream = streamRepository.findByStreamId(streamId);
			
		}catch (Exception e) {
			// log.error("Exception in Stream Service: findById {}", e.getMessage());
		}
		return stream;
	}

	/*
	 * @Override public Long getStreamIdFromStreamName(String streamName) {
	 * 
	 * // log.info("In Stream Service: getStreamIdFromStreamName {} ", streamName);
	 * 
	 * int streamId =0; Streams streamObj = new Streams();
	 * 
	 * try {
	 * 
	 * streamObj = streamRepository.findByStreamName(streamName); streamId =
	 * streamObj.getStreamId();
	 * 
	 * }catch (Exception e) {
	 * 
	 * // log.error("Exception in Stream Service: getStreamIdFromStreamName {}",
	 * e.getMessage()); }
	 * 
	 * return streamId; }
	 */

}
