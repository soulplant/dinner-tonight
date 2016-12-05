package bookr;

import bookr.ApiRequestModule.RequestUrl;
import bookr.core.PerRequest;
import bookr.api.ByeWorldModule;
import bookr.api.ExampleModule;
import bookr.api.HelloWorldModule;
import bookr.core.ApiDirectoryModule;
import dagger.Module;
import dagger.Provides;

import javax.inject.Provider;
import java.util.Map;

/** Exists to provide a Map<String, Provider<ApiResponse>>. */
@Module(includes = {
    HelloWorldModule.class,
    ByeWorldModule.class,
    ExampleModule.class,
    ApiDirectoryModule.class})
public class ApiRouterModule {
  @PerRequest
  @Provides
  ApiResponse provideResponse(@RequestUrl String url, Map<String, Provider<ApiResponse>> handlers) {
    for (String method : handlers.keySet()) {
      System.out.println("there's a handler for " + method);
    }
    String method = url.substring("/api/".length());
    Provider<ApiResponse> responseProvider = handlers.get(method);
    if (responseProvider == null) {
      return ApiResponse.create("handler not found");
    }
    return responseProvider.get();
  }
}
