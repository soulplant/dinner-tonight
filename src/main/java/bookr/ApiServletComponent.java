package bookr;

import bookr.servlet.ApiServlet;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component
public interface ApiServletComponent {
  ApiServlet servlet();

  ApiResponseComponent.Builder response();
}
