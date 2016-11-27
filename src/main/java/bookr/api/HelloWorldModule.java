package bookr.api;

import bookr.ApiRequestModule.RequestUrl;
import bookr.ApiResponse;
import bookr.core.PerRequest;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public class HelloWorldModule {
  @PerRequest
  @Provides
  @IntoMap
  @StringKey("hello")
  ApiResponse execute(@RequestUrl String requestUrl) {
    return ApiResponse.create("hello to " + requestUrl);
  }
}
