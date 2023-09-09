package br.com.noeleduk.noelproject.Dto.User;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class LoggedUserDto {
  private String token;
  private LocalDateTime tokenExpiration;
}
