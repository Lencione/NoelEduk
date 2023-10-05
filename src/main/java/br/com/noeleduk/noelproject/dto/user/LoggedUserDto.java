package br.com.noeleduk.noelproject.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoggedUserDto {
  private String token;
  private LocalDateTime tokenExpiration;
  private String role;
  private String document;
}
