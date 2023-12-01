package br.com.noeleduk.noelproject.controllers.healthcheck;

import br.com.noeleduk.noelproject.dto.response.ResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)

public class HealthcheckControllerTest {
    @InjectMocks
    private HealthcheckController healthcheckController;

    @Test
    public void loginUser_withValidEmailAndPassword_returnsLoggedUser() {
        String sut = healthcheckController.healthcheck();
        assertThat(sut).isEqualTo("OK");
    }

}
