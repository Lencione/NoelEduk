package br.com.noeleduk.noelproject.dto.lessons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserLessonsDto {
  private Date date;
  private String description;
  private String justification;
  private boolean exam;
  private boolean active;
}
