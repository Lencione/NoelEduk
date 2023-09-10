package br.com.noeleduk.noelproject.Config;

import br.com.noeleduk.noelproject.Interceptors.TeacherTokenInterceptor;
import br.com.noeleduk.noelproject.Interceptors.TokenInterceptor;
import br.com.noeleduk.noelproject.Services.TeacherService;
import br.com.noeleduk.noelproject.Services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
private final UserService userService;
private final TeacherService teacherService;
  public WebMvcConfig(UserService userService, TeacherService teacherService) {
    this.userService = userService;
    this.teacherService = teacherService;
  }
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new TokenInterceptor(this.userService))
            .addPathPatterns("/api/users/**")
            .excludePathPatterns("/api/teachers/register");

    registry.addInterceptor(new TeacherTokenInterceptor(this.teacherService))
            .addPathPatterns("/api/teachers/**")
            .excludePathPatterns("/api/teachers/register");
  }
}
