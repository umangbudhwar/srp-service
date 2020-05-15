package com.srp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srp.data.dto.StreamsDTO;
import com.srp.service.StreamsService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("api/rest/srp/stream")
@RestController
@Slf4j
public class StreamsController {

	@Autowired
	StreamsService streamsService;

	@GetMapping("/getStreams")
	public ResponseEntity<List<StreamsDTO>> getStreams() {
		log.info("In Stream Controller: getStreams()");
		List<StreamsDTO> streamDto = streamsService.getStreams();

		return ResponseEntity.ok(streamDto);
	}

}
