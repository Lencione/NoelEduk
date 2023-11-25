package br.com.noeleduk.noelproject.dto.lessons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFormattedLessonsDto<T> {
  private int week;
  private List<T> weekLessons;
}
