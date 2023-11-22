package br.com.noeleduk.noelproject.services;

import br.com.noeleduk.noelproject.dto.subjects.CreateSubjectDto;
import br.com.noeleduk.noelproject.entities.SubjectEntity;
import br.com.noeleduk.noelproject.entities.UserEntity;
import br.com.noeleduk.noelproject.repositories.SubjectRepository;
import br.com.noeleduk.noelproject.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Date;

import static org.mockito.Mockito.*;

class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LessonService lessonService;

    @InjectMocks
    private SubjectService subjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSubject() {
        // Arrange
        CreateSubjectDto createSubjectDto = new CreateSubjectDto("Subject Name", "Document", "Google Code", 1, LocalDate.now(), LocalDate.now());
        UserEntity teacher = new UserEntity(/* initialize fields as needed */);
        when(userRepository.findTeacherByDocument(createSubjectDto.getDocument())).thenReturn(teacher);
        when(subjectRepository.save(any(SubjectEntity.class))).thenReturn(new SubjectEntity());

        // Act
        subjectService.create(createSubjectDto);

        // Assert
        verify(userRepository, times(1)).findTeacherByDocument(createSubjectDto.getDocument());
        verify(subjectRepository, times(1)).save(any(SubjectEntity.class));
        // Add more assertions as needed based on your requirements
    }
}
