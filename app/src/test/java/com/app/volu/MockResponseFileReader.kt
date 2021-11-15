package com.app.volu

import java.io.FileNotFoundException

class MockResponseFileReader {
    /*    val content: String

        init {
           *//* val reader = InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(path))

        println("path ==== $reader")

        content = reader.readText()

        reader.close()*//*

        val reader = InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(path))
        content = reader.readText()
        reader.close()
    }*/
    companion object {
        fun getStringFromFile(filePath: String): String {
            val classLoader = this::class.java.classLoader

            if (classLoader != null) {
                try {
                    val inputString = classLoader.getResourceAsStream(filePath).bufferedReader()
                        .use { it.readText() }

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


    /*        val content: String

        init {
            val classLoader = this::class.java.classLoader

            if (classLoader != null) {
                try {
                    val inputString = classLoader.getResourceAsStream(path).bufferedReader().use { it.readText() }

                    println("Output from input file : $inputString")

                    content = inputString

                } catch (e: FileNotFoundException) {
                    println("Could not find the specified file: $path")
                    throw e
                }
            } else {
                throw IllegalStateException(
                    """Classloader is null. Can't open an inputstream for the specified file: $path without it."""
                )
            }
        }*/


    /*companion object {

        fun getJson(path: String): String {
            // Load the JSON response
            val uri = this.javaClass.classLoader.getResource(path)
            println("uri : $uri")
            val file = File(uri.path)
            return String(file.readBytes())
        }

        fun getContent(path: String?): String {
            val content: String
            val file = File(path)
            println(file.absolutePath)
            return try {
                val inputStream: InputStream = FileInputStream(file)
                val bytes = ByteArray(inputStream.available())
                inputStream.read(bytes)
                content = String(bytes)

                System.out.println(content);
                content
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        }

    }*/


/*    {
        "status": "success",
        "data": {
        "id": "6156f1df0a5c9f2395b781e3",
        "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Im9iaUB5YWhvby5jb20iLCJpYXQiOjE2MzU2NDExMTMsImV4cCI6MTYzNTY0NDcxMywiYXVkIjoib2JpQHlhaG9vLmNvbSIsImlzcyI6InZvbHUifQ.ZLepeTIyvvaJoh-s2t8A1xmpxn3n9pNk07g3qtD-gKc"
    }
    }

    {
        "status": "failed",
        "message": "username / password not valid"
    }*/

}