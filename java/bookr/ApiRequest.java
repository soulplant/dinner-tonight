package bookr;

import javax.servlet.http.HttpServletRequest;

public class ApiRequest {
  private final HttpServletRequest request;

  public ApiRequest(HttpServletRequest request) {
    this.request = request;
  }

  public HttpServletRequest getHttpRequest() {
    return request;
  }
}
