package com.pahanez.ppt.network

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

class CoroutinePlayground {

    @Test
    fun test1() = runBlocking {
        printThread("1")
        val job = launch {
            // launch a new coroutine in background and continue
            printThread("2")
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            println("World!") // print after delay
            printThread("3")
        }
        printThread("4")
        println("Hello,") // main thread continues while coroutine is delayed

        job.join()
//        Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
    }

    @Test
    fun test2() = runBlocking {
        printThread("1")
        val job = launch {
            repeat(1000) { i ->
            printThread("2")
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        }
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancel() // cancels the job
        job.join() // waits for job's completion
        println("main: Now I can quit.")
    }

    @Test
    fun testChannel() = runBlocking {
        val channel = Channel<Int>()
        launch {
            // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
            for (x in 1..5) channel.send(x * x)
        }
// here we print five received integers:
        repeat(5) { println(channel.receive()) }
        println("Done!")
    }

    fun printThread(tag: String) {
        println("$tag|thread: ${Thread.currentThread()}")
    }

    @Test
    fun test3() = runBlocking {
        //sampleStart
        val numbers = produceNumbers() // produces integers from 1 and on
        val squares = square(numbers) // squares integers
        for (i in 1..5) println(squares.receive()) // print first five
        println("Done!") // we are done
        coroutineContext.cancelChildren() // cancel children coroutines
//sampleEnd
    }

    fun CoroutineScope.produceNumbers() = produce<Int> {
        var x = 1
        while (true) send(x++) // infinite stream of integers starting from 1
    }

    fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
        for (x in numbers) send(x * x)
    }

    @Test
    fun test4() = runBlocking {
        //sampleStart
        var cur = numbersFrom(2, coroutineContext)
        for (i in 1..10) {
            val prime = cur.receive()
            println(prime)
            cur = filter(cur, prime, coroutineContext)
        }
        coroutineContext.cancelChildren() // cancel all children to let main finish
//sampleEnd
    }

    fun CoroutineScope.numbersFrom(start: Int, context: CoroutineContext) = produce(context) {
        var x = start
        while (true) {
            println("numbersFrom: x = $x")
            send(x++) // infinite stream of integers from start
        }
    }

    fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int, context: CoroutineContext) = produce(context) {
        println("filter $this")
        for (x in numbers) {
            if (x % prime != 0) {
                println("sending $x, prime = $prime")
                println("__________ ${Thread.currentThread()}")
                send(x)
            }
        }
    }

    @Test
    fun test5() = runBlocking<Unit> {
        //sampleStart
        val producer = produceNumbers2()
        repeat(5) { launchProcessor(it, producer) }
        delay(1950)
        producer.cancel() // cancel producer coroutine and thus kill them all
//sampleEnd
    }

    fun CoroutineScope.produceNumbers2() = produce<Int> {
        var x = 1 // start from 1
        while (true) {
            send(x++) // produce next
            delay(100) // wait 0.1s
        }
    }

    fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
        for (msg in channel) {
            println("Processor #$id received $msg")
        }
    }

    data class Ball(var hits: Int)

    @Test
    fun test6() = runBlocking {
        val table = Channel<Ball>() // a shared table
        launch { player("ping", table) }
        launch { player("pong", table) }
        table.send(Ball(0)) // serve the ball
        delay(1000) // delay 1 second
        coroutineContext.cancelChildren() // game over, cancel them
    }

    suspend fun player(name: String, table: Channel<Ball>) {
        for (ball in table) { // receive the ball in a loop
            ball.hits++
            println("$name $ball")
            delay(300) // wait a bit
            table.send(ball) // send the ball back
        }
    }

    @Test
    fun testExecutors(): Unit {
        val operationsCount = 3000000

        val countDownLatch = CountDownLatch(operationsCount)
        val corcountDownLatch = CountDownLatch(operationsCount)

        val executorTasks = mutableListOf<Runnable>()
        val coroutineTasks = mutableListOf<Runnable>()

        var execSum = 0
        var corSum = 0


        for (i in 0..operationsCount) {
            executorTasks.add(java.lang.Runnable {
                execSum = i
//                println("task: $i")
                countDownLatch.countDown()
            })
        }

        for (i in 0..operationsCount) {
            coroutineTasks.add(java.lang.Runnable {
                corSum = i
//                println("coroutine task: $i")
                corcountDownLatch.countDown()
            })
        }

        val executorsMS = System.currentTimeMillis()
        val threadPool = Executors.newFixedThreadPool(4)
        for (task in executorTasks) {
            threadPool.execute(task)
        }

        countDownLatch.await()
        println("executors done: ${System.currentTimeMillis() - executorsMS}ms")

        val coroutineMS = System.currentTimeMillis()

        repeat(operationsCount) { // launch a lot of coroutines
            GlobalScope.launch {
                corSum = it
                corcountDownLatch.countDown()
            }
        }

        /*for (i in 0..operationsCount) {
            GlobalScope.launch {
                taskTodo(1, corcountDownLatch)
            }
        }*/

        corcountDownLatch.await()
        println("coroutine done: ${System.currentTimeMillis() - coroutineMS}ms")

        println("$corSum $execSum")




    }

    suspend fun taskTodo(id: Int, countDownLatch: CountDownLatch) {
//        println("coroutine task: $id")
        countDownLatch.countDown()
    }



}