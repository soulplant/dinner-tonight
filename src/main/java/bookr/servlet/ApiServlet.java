package bookr.servlet;

import bookr.ApiRequestModule;
import bookr.ApiResponse;
import bookr.ApiResponseComponent;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** Servlet that serves the API. */
public class ApiServlet extends HttpServlet {
  private final Provider<ApiResponseComponent.Builder> responseBuilder;

  @Inject
  public ApiServlet(Provider<ApiResponseComponent.Builder> responseBuilder) {
    this.responseBuilder = responseBuilder;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ApiResponse apiResp = responseBuilder.get().apiRequest(new ApiRequestModule(req)).build().response();

    PrintWriter writer = resp.getWriter();
    writer.write(apiResp.getData());
    writer.close();
  }
}
