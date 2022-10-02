# Description

Another template using scala-cli which tries to have a more complex
structure with a more sane and reasonable layout closer to other
languages, with simple folders as "bin" and "lib".

Also adds package command that should be useful to generate a
release artifact to be distributed to users.

## Identified issues and workarounds:

  * initially tried using a bin/main.sc file, but scala/cli keeps complaining
    about two/multiple mains existing, so had to convert to a normal looking
    main.scala using the new scala 3 @main annotation for it to work properly. seems a bug.
  * ~using scala native, can't do "package", fails at linking.~ probably was something related
    to not having proper clang installed. Seems working now.
  * can't use a complex path on the output, keeps complaining about file / path not found.
    Basically -o option needs to be a simple file, something like "build/app.bin" doesn't work.
    smells like bug.
  * it would be better to have another package mode with a .jar + libs dir + script launcher,
    but doesn't seem supported. assmebly seems to be the more correct one, since at least
    it's not doing that crazy thing about downloading files on demand at the start of the app.
  * tried munit as test lib, but stable release doesnt seem to work for scala native or something.
    tried with M version, it downloads ok but hangs at the end of the `just test` command. Using
    weaver seems to work. seems a bug on latest 1.0.0-M version of munit.
  * having a packagingType defined in the settings file makes it impossible to try to package
    scala native binary since it keeps complaining about assembly not being a valid thing.
    workaround is yet again moving the thing to the command in the justfile and use the flags 
    -- assembly instead. This way scala native packaging works.
  * trying to use the file directives seems pretty broken or not really working as expected
    when just trying to call scala-cli directly pointing to either the project.settings.scala
    directly or the . directory and then trying to include other folders like the example folder.
    The workaround is to not use that directive and control the files and folders in use directly
    in the justfile by being explicit.

## Build / Development

### Requirements

  * jvm (optional) - either an installation with proper `JAVA_HOME` correctly set or the tool auto
    manages and downloads a jvm itself for usage
  * clang (optional) - this is needed for the targets related to compile and use scala-native. better
    check upstream scala-native docs about needed libs on the machine.
  * [scala-cli](https://scala-cli.virtuslab.org/) (tested with version 0.1.15)
  * [just](https://github.com/casey/just)


Run `just` in the console at the root of this project and it will tell available commands.
Currently basicly wrapping the scala-cli commands which are already simple enough, but
this way you don't even need to care about that. Also useful to learn scala-cli tool itself.

# License

This is just a template/example, so most permissible possible is applied.
All files and things here should be considered **CC0**.
