package br.com.noeleduk.noelproject.dto.subjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddClassToSubjectDto {
  private List<UUID> classes;
}
