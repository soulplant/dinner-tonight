package bookr;

import bookr.core.PerRequest;
import dagger.Module;
import dagger.Provides;

import javax.servlet.http.HttpServletRequest;

@Module
public class ApiRequestModule {
  public @interface RequestUrl{}

  private final HttpServletRequest request;

  public ApiRequestModule(HttpServletRequest request) {
    this.request = request;
  }

  @PerRequest
  @Provides
  @RequestUrl
  String provideRequestUrl() {
    return request.getRequestURI();
  }

  @PerRequest
  @Provides
  ApiRequest provideRequest() {
    return new ApiRequest(request);
  }
}
