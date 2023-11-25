package br.com.noeleduk.noelproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetStudentCardDto {
  String name;
  String document;
  String rg;
  String cpf;
  String validate;
  String course = null;
  String avatar;
}
