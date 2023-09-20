package br.com.noeleduk.noelproject.dto.subjects;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AddClassToSubjectDto {
  private UUID class_id;
}
