package org.prog.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDto {
    private List<PersonDto> results;
}
