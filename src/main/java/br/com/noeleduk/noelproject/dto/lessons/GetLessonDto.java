package br.com.noeleduk.noelproject.dto.lessons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLessonDto {
  private UUID id;
  private LocalDate date;
  private String description;
  private String subjectName;
  private String subjectTeacherName;
  private String justification;
  private boolean exam;
  private boolean active;
  private int weekOfYear;
}
