java_binary(
    name = "bookr",
    srcs = glob(["**/*.java"]),
    main_class = "bookr.Main",
    deps = [
      "//third_party:io_dropwizard_dropwizard_core",
      ":dagger"
    ],
)

# TODO(james): See if we can use generate_workspace to get dagger and friends.
java_library(
    name = "dagger",
    exported_plugins = [":dagger_plugin"],
    exports = [
        ":auto_value",
        "@dagger//jar",
        "@inject//jar",
    ],
)

java_plugin(
    name = "dagger_plugin",
    deps = [
        ":auto_value",
        "@dagger//jar",
        "@dagger_compiler//jar",
        "@dagger_producers//jar",
        "@inject//jar",
        "@guava//jar",
    ],
    processor_class = 'dagger.internal.codegen.ComponentProcessor',
)

java_library(
    name = "auto_value",
    exported_plugins = [
        ":auto_value_plugin",
        ":auto_value_plugin_builder",
        ":auto_value_plugin_annotation"
    ],
    exports = [
        "@auto_value//jar",
    ]
)

java_plugin(
    name = "auto_value_plugin_value",
    deps = [
        "@auto_value//jar",
    ],
    processor_class = 'com.google.auto.value.processor.AutoValueProcessor',
)

java_plugin(
    name = "auto_value_plugin_builder",
    deps = [
        "@auto_value//jar",
    ],
    processor_class = 'com.google.auto.value.processor.AutoValueBuilderProcessor',
)

java_plugin(
    name = "auto_value_plugin_annotation",
    deps = [
        "@auto_value//jar",
    ],
    processor_class = 'com.google.auto.value.processor.AutoAnnotationProcessor',
)
