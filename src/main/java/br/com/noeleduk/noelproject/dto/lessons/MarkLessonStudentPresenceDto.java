package br.com.noeleduk.noelproject.dto.lessons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkLessonStudentPresenceDto {
  private List<String> students;
}
