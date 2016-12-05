package bookr;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Map;

public class ApiRequestRouter {
  private final Map<String, Provider<ApiResponse>> endpointMap;

  @Inject
  public ApiRequestRouter(Map<String, Provider<ApiResponse>> endpointMap) {
    this.endpointMap = endpointMap;
  }

  public ApiResponse handleRequest(ApiRequest request) {
    for (String method : endpointMap.keySet()) {
      System.out.println("there's a handler for " + method);
    }
    String method = request.getHttpRequest().getRequestURI().substring("/api/".length());
    Provider<ApiResponse> responseProvider = endpointMap.get(method);
    if (responseProvider == null) {
      return ApiResponse.create("handler not found");
    }
    return responseProvider.get();
  }
}
