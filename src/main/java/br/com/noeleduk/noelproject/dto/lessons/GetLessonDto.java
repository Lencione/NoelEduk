package br.com.noeleduk.noelproject.dto.lessons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLessonDto {
  private UUID id;
  private Date date;
  private String description;
  private String justification;
  private boolean exam;
  private boolean active;
}
