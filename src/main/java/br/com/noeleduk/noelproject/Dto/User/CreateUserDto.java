package br.com.noeleduk.noelproject.Dto.User;

import lombok.Data;

@Data
public class CreateUserDto {
    private String email;
    private String password;
    private String cpf;
    private String rg;
    private String name;
    private String phone;
    private String document;
    private String avatar;
}
