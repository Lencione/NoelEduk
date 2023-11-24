package br.com.noeleduk.noelproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestDto {
  private String email;
  private String password;
}
