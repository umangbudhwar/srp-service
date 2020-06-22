package com.srp.data.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect
public class StreamsDTO {
	
	private Long streamId;
	private String streamName;

}
