package bookr.api;

import bookr.ApiRequestModule.RequestUrl;
import bookr.ApiResponse;
import bookr.core.PerRequest;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

import java.io.File;

@Module
public class HelloWorldModule {
  @PerRequest
  @Provides
  @IntoMap
  @StringKey("hello")
  ApiResponse execute(@RequestUrl String requestUrl) {
    return ApiResponse.create("hello to " + requestUrl + ", from " + new File(".").getAbsolutePath());
  }
}
