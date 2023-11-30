package br.com.noeleduk.noelproject.dto.lessons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserLessonsDto {
  private LocalDate date;
  private String lessonId;
  private String subjectName;
  private String subjectTeacherName;
  private String description;
  private String justification;
  private boolean status;
  private boolean exam;
  private boolean active;
  private int weekOfYear;
}
