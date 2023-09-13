package br.com.noeleduk.noelproject.Dto.Lessons;

import br.com.noeleduk.noelproject.Entities.SubjectEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CreateLessonDto {
  private Date date;
  private SubjectEntity subject;
}
