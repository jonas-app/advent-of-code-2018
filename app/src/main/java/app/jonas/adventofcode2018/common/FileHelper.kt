package app.jonas.adventofcode2018.common

import java.io.File

// loads resources from the same package out of the resource directory
// inlining the function helps extracting the path
// this only works with classes, on top level you have to pass an empty object to make it work
@Suppress("NOTHING_TO_INLINE")
inline fun load(name: String, ref: Any = object {}): File {
    val parentPackage = ref.javaClass.`package`?.name?.replace('.', '/')
    return File(ClassLoader.getSystemClassLoader().getResource("$parentPackage/$name").toURI())
}
