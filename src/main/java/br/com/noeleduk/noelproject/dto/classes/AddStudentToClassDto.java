package br.com.noeleduk.noelproject.dto.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStudentToClassDto {
  private List<String> documents;
}
