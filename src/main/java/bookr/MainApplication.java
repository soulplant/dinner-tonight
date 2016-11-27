package bookr;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

public class MainApplication extends Application<MainConfiguration> {

  @Override
  public void initialize(Bootstrap<MainConfiguration> bootstrap) {
    bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
        new EnvironmentVariableSubstitutor()));
  }

  @Override
  public void run(MainConfiguration configuration, Environment environment) throws Exception {
    environment.jersey().disable();
    ServletRegistration.Dynamic servlet = environment.servlets().addServlet("api", new HttpServlet() {
      @Override
      public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();
        writer.println("HI");
        writer.close();
      }
    });
    servlet.addMapping("/api/*");
    servlet.setAsyncSupported(true);
  }
}
