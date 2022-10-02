name := `pwd | awk -F/ '{print $NF}'`

settingsfile := "project.settings.scala"
settingsfile-with-native := settingsfile + " " + "project.settings.native.scala"

bindirs     := "bin"
libdirs     := "lib"
srcdirs     := bindirs + " "  + libdirs
testdirs    := "tests"
exampledirs := "examples"
docsdirs    := "docs"


# Lists available commands to run for this application.
default:
  @just --list

# Builds the core files and reports compilation errors.
build:
  scala-cli compile {{ settingsfile }} {{ srcdirs }}

# Experiment if build works under scala native.
build-native:
  scala-cli compile {{ settingsfile-with-native }} {{ srcdirs }}

# Run the main of the application.
run:
  scala-cli run {{ settingsfile }} {{ srcdirs }}

# Experiment running as scala-native.
run-native:
  scala-cli run {{ settingsfile-with-native }} {{ srcdirs }}
 
# Clean any artifacts from building the file.
clean:
  scala-cli clean {{ settingsfile }}

# Open a scala repl shell with application code available for use.
console:
  scala-cli console {{ settingsfile }} {{ srcdirs }}

# Runs tests of the application. Tests should be in {{testdirs}} and with suffix '.test.scala'
test:
  scala-cli test {{ settingsfile }} {{ srcdirs }} {{ testdirs }}

# Packages the application in an executable that needs jvm to run.
package:
  scala-cli package --assembly {{ settingsfile }} {{ srcdirs }} --force --output {{ name }}.jvm.bin

# Packages the application in an executable compiled to machine code using scala-native.
package-native:
  scala-cli package {{ settingsfile-with-native }} {{ srcdirs }} --force --output {{ name }}.native.bin

# Format application source files. Check .scalafmt.conf for formatting settings.
format:
  scala-cli fmt {{ settingsfile }} {{ srcdirs }} {{ exampledirs }} {{ testdirs }}

# Checks if application files are properly formated. Check .scalafmt.conf for formatting settings.
format-check:
  scala-cli fmt --check {{ settingsfile }} {{ srcdirs }} {{ exampledirs }} {{ testdirs }}

# List examples available and run a specific example.
run-example EXAMPLE="":
  #!/bin/env bash
  main="{{EXAMPLE}}"
  if [ -z "$main" ];
  then
    echo "Available examples:"
    scala-cli run {{ settingsfile }} {{ libdirs }} {{ exampledirs }} --list-main-classes
  else
    scala-cli run {{ settingsfile }} {{ libdirs }} {{ exampledirs }} --main-class $main
  fi

