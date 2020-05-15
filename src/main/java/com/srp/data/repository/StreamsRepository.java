package com.srp.data.repository;

import org.springframework.stereotype.Repository;

import com.srp.configuration.jpa.SrpRepository;
import com.srp.data.entity.Streams;

@Repository
public interface StreamsRepository extends SrpRepository<Streams, Integer> {

//	Streams findByStreamName(String streamName);

}
