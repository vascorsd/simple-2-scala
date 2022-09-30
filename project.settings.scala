// ---- Basic language settings ----
//> using scala "3.2.0"

// ---- File to include and platform to use ----
//! wanted to test native, but the following fails
// packaging linking.
//!> using platform "scala-native"
//!> using nativeVersion "0.4.7"

// not needed to be explicit...
//!!!!> using file "bin" "lib"

// ---- Packaging settings ----

// wanted to have a normal thing with the .jar, a folder with the libs
// and normal simple script files that would wrap the jar + libs, but seems nor supported.
// assembly seems to be the closest to sanity which is sad.

//!!> using packaging.packageType "raw-assembly"
//> using packaging.packageType "assembly"

// ANOTHER BUG: cant use a path more complex
// like "target/thing" or anything else, it just fails.

//> using packaging.output "release"
