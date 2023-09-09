package br.com.noeleduk.noelproject.Dto.User;

import lombok.Data;

@Data
public class LoginRequestDto {
  private String email;
  private String password;
}
