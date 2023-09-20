package br.com.noeleduk.noelproject.dto.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetClassDto {
  private UUID id;
  private String name;
  private int semester;
}
