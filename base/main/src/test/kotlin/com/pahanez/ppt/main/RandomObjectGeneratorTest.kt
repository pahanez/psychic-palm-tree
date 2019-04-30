package com.pahanez.ppt.main

import com.pahanez.ppt.main.util.RandomObjectGenerator
import org.junit.Test

class RandomObjectGeneratorTest {

    @Test
    fun test1() {
        println("test")
        val generator = RandomObjectGenerator()

        val result = generator.makeRandomInstance<B>()
        println("result $result")
    }

    data class A(val name: String, val age: Int)
    data class B(val a1: A, val a2: A)
}