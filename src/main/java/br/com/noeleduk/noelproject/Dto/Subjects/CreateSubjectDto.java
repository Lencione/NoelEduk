package br.com.noeleduk.noelproject.Dto.Subjects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateSubjectDto {
  private String name;
  private String document;
  private String google_code;
  private int week_day;
}
