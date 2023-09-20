package br.com.noeleduk.noelproject.dto.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClassDto {
  private String name;
  private int semester;
}
