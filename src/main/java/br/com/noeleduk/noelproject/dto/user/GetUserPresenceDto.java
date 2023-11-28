package br.com.noeleduk.noelproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserPresenceDto {
  private String subjectName;
  private Long presences;
  private Long fouls;
  private Long total;
  private String googleCode;
  private String teacherName;
}
