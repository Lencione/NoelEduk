package br.com.noeleduk.noelproject.dto.user;

import lombok.Data;

@Data
public class LoginRequestDto {
  private String email;
  private String password;
}
