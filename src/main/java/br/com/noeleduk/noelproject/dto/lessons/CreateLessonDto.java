package br.com.noeleduk.noelproject.dto.lessons;

import br.com.noeleduk.noelproject.entities.SubjectEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreateLessonDto {
  private LocalDate date;
  private SubjectEntity subject;
}
