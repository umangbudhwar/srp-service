package com.srp.data.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonAutoDetect
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class StudentGroupDTO {
    private String userName;
    private String groupNumber;
}
