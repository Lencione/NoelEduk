package br.com.noeleduk.noelproject.Services;

import br.com.noeleduk.noelproject.Dto.Subjects.CreateSubjectDto;
import br.com.noeleduk.noelproject.Entities.SubjectEntity;
import br.com.noeleduk.noelproject.Entities.UserEntity;
import br.com.noeleduk.noelproject.Repositories.SubjectRepository;
import br.com.noeleduk.noelproject.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
  private final SubjectRepository repository;
  private final UserRepository userRepository;
  @Autowired
  public SubjectService(SubjectRepository repository, UserRepository userRepository) {
    this.repository = repository;
    this.userRepository = userRepository;
  }

  public boolean create(CreateSubjectDto request) {
    UserEntity teacher = userRepository.findTeacherByDocument(request.getDocument());
    if (teacher == null) {
      throw new RuntimeException("Teacher document is no valid");
    }
    if(!teacher.getRole().equals("teacher")){
      throw new RuntimeException("document is not a teacher");
    }
    SubjectEntity subject = new SubjectEntity();
    subject.setName(request.getName());
    subject.setTeacher(teacher);
    subject.setGoogleCode(request.getGoogle_code());
    subject.setWeek_day(request.getWeek_day());
    this.repository.save(subject);
    return true;
  }


}
