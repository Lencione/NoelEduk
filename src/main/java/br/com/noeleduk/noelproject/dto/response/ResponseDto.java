package br.com.noeleduk.noelproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String message;
    private boolean status;
    private Object data;
}
