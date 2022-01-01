package com.app.volu

import java.io.FileNotFoundException

class MockResponseFileReader {

    companion object {

        fun content(filePath: String): String {
            val classLoader = this::class.java.classLoader

            if (classLoader != null) {
                try {
                    val inputString = classLoader.getResourceAsStream(filePath)
                        .bufferedReader()
                        .use {
                            it.readText()
                        }

                    println(" $inputString")

                    return inputString
                } catch (e: FileNotFoundException) {
                    println("Could not find the specified file: $filePath")
                    throw e
                }
            } else {
                throw IllegalStateException(
                    """Classloader is null. Can't open an inputstream for the specified file: $filePath without it."""
                )
            }
        }

    }
}