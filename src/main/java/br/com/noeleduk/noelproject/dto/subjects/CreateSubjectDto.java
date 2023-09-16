package br.com.noeleduk.noelproject.dto.subjects;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreateSubjectDto {
  private String name;
  private String document;
  private String google_code;
  private int week_day;
  private LocalDate start_date;
  private LocalDate end_date;
}
