package br.com.noeleduk.noelproject.Dto.User;

import lombok.Data;

@Data
public class UserResponseDto {
    private String name;
    private String email;
    private String document;
    private String edukoins;
    private String avatar;
    private String points;
}
