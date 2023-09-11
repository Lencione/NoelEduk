package br.com.noeleduk.noelproject.Dto.Classes;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AddStudentToClassDto {
  private UUID classId;
  private String studentRa;
}
