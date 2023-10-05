package br.com.noeleduk.noelproject.dto.subjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSubjectDto {
  UUID id;
  String name;
  String description;
  String teacherName;
  String teacherDocument;
  String googleCode;
}
