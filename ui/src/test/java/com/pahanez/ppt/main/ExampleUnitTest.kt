package com.pahanez.ppt.main

import org.junit.Test
import java.net.URL
import java.nio.charset.Charset

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        println(URL("http://mobitee.com").readText(Charset.defaultCharset()))
    }
}
