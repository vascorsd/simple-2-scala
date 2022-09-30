# Description

Another template using scala-cli which tries to have a more complex
structure with a more sane and reasonable layout closer to other
languages, with simple folders as "bin" and "lib".

Also adds package command that should be useful to generate a
release artifact to be distributed to users.

## Identfied issues:

  * initially tried using a bin/main.sc file, but scala/cli keeps complaining
    about two/multiple mains existing, so had to convert to a normal looking
    main.scala using the new scala 3 @main annotation for it to work properly.
  * using scala native, can't do "package", fails at linking.
  * can't use a complex path on the output, keeps complaining about file / path not found.
  * it would be better to have another pacakge mode with a .jar + libs dir + script launcher,
    but doesn't seem supported.

## Build / Development

### Requirements

  * jvm (optional) - either an installation with proper `JAVA_HOME` correctly set or the tool auto
    manages and downloads a jvm itself for usage
  * [scala-cli](https://scala-cli.virtuslab.org/) (tested with version 0.1.15)
  * [just](https://github.com/casey/just)


Run `just` in the console at the root of this project and it will tell available commands.
Currently basicly wrapping the scala-cli commands which are already simple enough, but
this way you don't even need to care about that. Also useful to learn scala-cli tool itself.

# License

This is just a template/example, so most permissible possible is applied.
All files and things here should be considered **CC0**.
