package com.srp.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "streams")
@Data
public class Streams {
	
	@Id
	@Column(name = "stream_id")
	private Integer streamId;
	
	@Column(name = "stream_name")
	private String streamName;
	
}
