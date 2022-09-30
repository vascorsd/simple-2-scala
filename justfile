srcdir := "."

default:
  @just --list

build:
  scala-cli compile {{srcdir}}

clean:
  scala-cli clean {{srcdir}}

run:
  scala-cli run {{srcdir}}

console:
  scala-cli console {{srcdir}}

format:
  scala-cli fmt {{srcdir}}

format-check:
  scala-cli fmt --check {{srcdir}}

package:
  scala-cli package {{srcdir}} --force
