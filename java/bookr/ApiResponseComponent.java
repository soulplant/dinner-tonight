package bookr;

import bookr.core.PerRequest;
import dagger.Subcomponent;

@PerRequest
@Subcomponent(modules = {ApiRequestModule.class, ApiRouterModule.class})
public interface ApiResponseComponent {
  ApiResponse response();

  @Subcomponent.Builder
  interface Builder {
    Builder apiRequest(ApiRequestModule module);
    ApiResponseComponent build();
  }
}
