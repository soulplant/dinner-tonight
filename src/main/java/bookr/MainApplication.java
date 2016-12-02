package bookr;

import bookr.servlet.ApiServlet;
import bookr.servlet.FileServlet;
import com.google.common.collect.ImmutableMap;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.servlet.ServletRegistration;
import java.io.*;

public class MainApplication extends Application<MainConfiguration> {

  @Override
  public void initialize(Bootstrap<MainConfiguration> bootstrap) {
    bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
        new EnvironmentVariableSubstitutor()));
  }

  @Override
  public void run(MainConfiguration configuration, Environment environment) throws Exception {
    environment.jersey().disable();
    FileServlet fileServlet = new FileServlet("", new File("client/dist/"));
    fileServlet.addMimeTypes(
        ImmutableMap.<String, String>builder()
            .put("css", "text/css")
            .build());
    ApiServlet apiServlet = new ApiServlet();

    ServletRegistration.Dynamic registration = environment.servlets().addServlet("app", fileServlet);
    registration.addMapping("/*");
    registration.setAsyncSupported(true);

    registration = environment.servlets().addServlet("api", apiServlet);
    registration.addMapping("/api/*");
    registration.setAsyncSupported(true);
  }
}
