package br.com.noeleduk.noelproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LoggedUserDto {
  private String token;
  private LocalDateTime tokenExpiration;
  private String role;
  private String document;
  private String course;
}
