// ---- Basic language settings ----
//> using scala "3.2.0"

// ---- Testing settings ----
// somehow munit hangs at the end of calling test.

//!> using lib "org.scalameta::munit::1.0.0-M6"
//!> using testFramework "munit.Framework"

//> using lib "com.disneystreaming::weaver-cats::0.8.0"
//> using testFramework "weaver.framework.CatsEffect"

// ---- Packaging settings ----

// YET Again another problem.
// if this is defined explicitly then if I try to package scala native
// it conflicts and keeps gettting annoying error about assembly pakcatype not supported.
// so yet again something that needs to be told at command line instead of a using directive
// it seems.

//!> using packaging.packageType "assembly"
