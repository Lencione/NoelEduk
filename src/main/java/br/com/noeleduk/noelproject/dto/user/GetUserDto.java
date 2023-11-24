package br.com.noeleduk.noelproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserDto {
    private String name;
    private String email;
    private String document;
    private String edukoins;
    private String avatar;
    private String points;
}
