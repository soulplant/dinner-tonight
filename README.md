To make it so you can run the server from IntelliJ

1. Run > Edit configurations.
2. Add "server dev.yml" to command line arguments.
3. Add Environment variables: PORT=9999


# Client

To build / run the client we use angular-cli. Follow the instructions at https://github.com/angular/angular-cli.

Client is in the client/ directory. For a dev process:

1. npm install -g angular-cli
1. cd client
1. ng serve

# Backend

Heroku app is dinner-tonight.
Github is github.com/soulplant/dinner-tonight.

# Annotation Processing

To make annotation processing work properly in IntelliJ:

1. Command-,
2. Search for Annotation Processors
3. "Enable annotation processing" to true
4. Set "Module content root"
5. "Production sources directory" to build/generated/source/apt/main
6. "Test sources directory" to build/generated/source/apt/test
7. Done.
8. Command-;
9. Add build/generated/source/apt/main as a source directory (this may involve un-excluding build/).
