package br.com.noeleduk.noelproject.dto.lessons;

import br.com.noeleduk.noelproject.entities.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CreateLessonDto {
  private Date date;
  private Subject subject;
}
