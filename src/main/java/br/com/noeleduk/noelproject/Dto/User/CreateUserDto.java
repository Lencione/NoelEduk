package br.com.noeleduk.noelproject.Dto.User;

import lombok.Data;

@Data
public class CreateUserDto {
    private String email;
    private String password;
    private String token;
    private String tokenExpiration;
    private String cpf;
    private String rg;
    private String name;
    private String phone;
    private String role;
    private String document;
    private int edukoins;
    private String avatar;
    private int points;
}
