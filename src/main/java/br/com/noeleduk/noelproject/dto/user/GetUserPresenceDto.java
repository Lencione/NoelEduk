package br.com.noeleduk.noelproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserPresenceDto {
  private String subject;
  private int presences;
  private int fouls;
  private int total;
}
