package br.com.noeleduk.noelproject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String message;
    private String status;
    private Object data;
}
