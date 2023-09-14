package br.com.noeleduk.noelproject.Dto.Subjects;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AddClassToSubjectDto {
  private UUID subject_id;
  private UUID class_id;
}
