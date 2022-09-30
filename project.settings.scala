// ---- Basic language settings ----
//> using scala "3.2.0"

// ---- File to include and platform to use ----
//! wanted to test native, but the following fails
// packaging linking.
//!> using platform "scala-native"
//!> using nativeVersion "0.4.7"

// Only include the code folders we want.
// this feature seems uterly broken, since I can't exclude a directory
// I'm not interested in. not sure if its because this settings file exists
// or these directives being ignored.
// Example use case i wanted was:
//  - having multiple folders to organize the code
//  - having and example or docs folder excluded from the compilation and warnings
//    about too many mains
//  - then sometimes explicit call the for example the examples folder with scala-cli run . examples --list-mains or wtv.
// maybe the solution goes by using a different root folder for that? but then I'd need different project.settings files ???

// also putting using file "bin" "lib" in same line is failling? are docs broken or feature broken?

// EDIT!: this actually works if instead of passing to scala-cli run the current folder I pass this
// file explicitly.
// so:
//  - scala-cli run . is WRONG!
//  - scala-cli run project.settings.scala WORKS
//  - scala-cli run project.settings.scala examples GOOD

// EDIT AGAIN. forget it. better control expliitly on scala-cli call since
// for example listing the main files to run is utterly weird since it keeps
// including the main itself.

//!!> using file "bin"
//!!> using file "lib"

// ---- Testing settings ----

//> using lib "org.scalameta:::munit::0.7.29"

// ---- Packaging settings ----

// wanted to have a normal thing with the .jar, a folder with the libs
// and normal simple script files that would wrap the jar + libs, but seems nor supported.
// assembly seems to be the closest to sanity which is sad.

//!!> using packaging.packageType "raw-assembly"
//> using packaging.packageType "assembly"

// ANOTHER BUG: cant use a path more complex
// like "target/thing" or anything else, it just fails.

// better control in the justfile too ???
//!!!> using packaging.output "release"
