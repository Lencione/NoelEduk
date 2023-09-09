package br.com.noeleduk.noelproject.Interceptors;

import br.com.noeleduk.noelproject.Services.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
  private final UserService userService;

  public TokenInterceptor(UserService userService) {
    this.userService = userService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
    String token = request.getHeader("Authorization");

    if (token != null && userService.validateToken(token)) {
      return true;
    }
    response.sendError(403);
    return false;
  }
}
