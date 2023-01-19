name := `pwd | awk -F/ '{print $NF}'`

scala-cli-bin := "scala-cli"

settings-file := "project.scala"
native-settings-file := "project.native.scala"
all-settings-files := settings-file + " " + native-settings-file

bindirs     := "bin"
libdirs     := "lib"
srcdirs     := bindirs + " " + libdirs
testdirs    := "tests"
exampledirs := "examples"
docsdirs    := "docs"

alias b       := build
alias compile := build
alias c       := build
alias check   := build

alias bn             := build-native
alias compile-native := build-native
alias cn             := build-native
alias check-native   := build-native

alias r  := run
alias rn := run-native

alias p  := package
alias pn := package-native

alias fmt := format

# Lists available commands to run for this application.
_default:
  @just --list

_build *files:
  {{ scala-cli-bin }} compile {{ settings-file }} {{ files }} {{ srcdirs }}

_run *files:
  {{ scala-cli-bin }} run {{ settings-file }} {{ files }} {{ srcdirs }}

_package ext *files:
  {{ scala-cli-bin }} package --assembly {{ settings-file }} {{ files }} {{ srcdirs }} \
    --force --output {{ name }}.{{ ext }}

# Builds the core files and reports compilation errors.
build: (_build)

# Builds under scala native.
build-native: (_build native-settings-file)

# Run the main of the application.
run: (_run)

# Run as scala-native.
run-native: (_run native-settings-file)
 
# Clean any artifacts from building the file.
clean:
  scala-cli clean {{ settings-file }}

# Open a scala repl shell with application code available for use.
console:
  scala-cli console {{ settings-file }} {{ srcdirs }}

# Runs tests of the application. Tests should be in {{testdirs}} and with suffix '.test.scala'
test:
  scala-cli test {{ settings-file }} {{ srcdirs }} {{ testdirs }}

# Packages the application in an executable that needs jvm to run.
package: (_package "jvm.bin")

# Packages the application in an executable compiled to machine code using scala-native.
package-native: (_package "native.bin" native-settings-file)

# Format application source files. Check .scalafmt.conf for formatting settings.
format:
  {{ scala-cli-bin }} fmt {{ settings-file }} {{ srcdirs }} {{ exampledirs }} {{ testdirs }}

# Checks if application files are properly formated. Check .scalafmt.conf for formatting settings.
format-check:
  {{ scala-cli-bin }} fmt --check {{ settings-file }} {{ srcdirs }} {{ exampledirs }} {{ testdirs }}

# List examples available and run a specific example.
run-example example="":
  #!/bin/env bash
  MAIN="{{ example }}"

  if [ -z "$MAIN" ];
  then
    echo "Available examples:"

    {{ scala-cli-bin }} run {{ settings-file }} {{ libdirs }} {{ exampledirs }} --list-main-classes
  else
    {{ scala-cli-bin }} run {{ settings-file }} {{ libdirs }} {{ exampledirs }} --main-class "$MAIN"
  fi

# Check if there are updates to the libraries in the project
updates-check:
  {{ scala-cli-bin }} dependency-update {{ settings-file }} {{ srcdirs }} {{ testdirs }} {{ exampledirs }} 
