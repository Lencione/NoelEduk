package br.com.noeleduk.noelproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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
