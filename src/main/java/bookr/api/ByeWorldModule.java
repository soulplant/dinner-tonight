package bookr.api;

import bookr.ApiRequestModule.RequestUrl;
import bookr.ApiResponse;
import bookr.core.PerRequest;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public class ByeWorldModule {
  @PerRequest
  @Provides @IntoMap
  @StringKey("bye")
  ApiResponse execute(@RequestUrl String requestUrl) {
    return ApiResponse.create("bye " + requestUrl);
  }
}
