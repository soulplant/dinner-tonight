package bookr.core;

import dagger.MapKey;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/** TODO(james): Come up with a better name. */
@Retention(RetentionPolicy.RUNTIME)
@MapKey(unwrapValue = false)
public @interface Endpoint {
  String path();
  Class<?> request() default Object.class;
  Class<?> response() default Object.class;
}
