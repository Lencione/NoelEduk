package br.com.noeleduk.noelproject.Config;

import br.com.noeleduk.noelproject.Interceptors.TokenInterceptor;
import br.com.noeleduk.noelproject.Services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
private final UserService userService;
  public WebMvcConfig(UserService userService) {
    this.userService = userService;
  }
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new TokenInterceptor(this.userService)).addPathPatterns("/api/users/**");
  }
}
