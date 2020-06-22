package com.srp.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "streams")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Streams {
	
	@Id
	@Column(name = "stream_id")
	private Long streamId;
	
	@Column(name = "stream_name")
	private String streamName;
	
}
