package bookr;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import dagger.multibindings.StringKey;

import javax.inject.*;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.time.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

  public static void main(String[] args) throws Exception {
    new MainApplication().run(args);
  }
}
