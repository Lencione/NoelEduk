package br.com.noeleduk.noelproject.Interceptors;

import br.com.noeleduk.noelproject.Services.TeacherService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TeacherTokenInterceptor implements HandlerInterceptor {
  private final TeacherService teacherService;

  public TeacherTokenInterceptor(TeacherService teacherService) {
    this.teacherService = teacherService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
    String token = request.getHeader("Authorization");

    if (token != null && teacherService.validateToken(token)) {
      return true;
    }
    response.sendError(403);
    return false;
  }
}
