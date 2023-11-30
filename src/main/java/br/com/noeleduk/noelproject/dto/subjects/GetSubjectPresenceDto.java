package br.com.noeleduk.noelproject.dto.subjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSubjectPresenceDto {
  private String subjectName;
  private int total;
  private int presences;
  private int faults;
}
