package bookr.core;


import bookr.ApiResponse;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

import javax.inject.Provider;
import java.util.Map;

@Module
public class ApiDirectoryModule {
  @PerRequest
  @Provides
  @IntoMap
  @StringKey("debugz/api")
  ApiResponse execute(Map<String, Provider<ApiResponse>> handlerMap) {
    StringBuilder builder = new StringBuilder();
    builder.append("<style>body { font-family: monospace; }</style>");
    builder.append("<p>Request handlers\n");
    builder.append("<ul>");
    for (String path : handlerMap.keySet()) {
      builder.append("<li>" + path + "</li>");
    }
    builder.append("</ul>");
    return ApiResponse.create(builder.toString());
  }
}
