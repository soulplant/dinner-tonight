package bookr.api;

import bookr.ApiRequestModule.RequestUrl;
import bookr.ApiResponse;
import bookr.core.PerRequest;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public class ExampleModule {
  @PerRequest
  @Provides
  @IntoMap
  @StringKey("example")
  ApiResponse provideApiResponse(@RequestUrl String url) {
    return ApiResponse.create("I'm an example page, served from " + url);
  }
}
