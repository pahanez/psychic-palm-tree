package com.pahanez.ppt.network.statemachine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking
import org.junit.Test

data class State(val count: Int = 0)

interface Updater {
    fun update(prevState: State): State
}

class Increment: Updater {

    override fun update(prevState: State): State {
        val count = prevState.count
        return prevState.copy(count = count + 1)
    }
}

class Decrement: Updater {

    override fun update(prevState: State): State {
        val count = prevState.count
        return prevState.copy(count = count - 1)
    }
}



class StateMachineTest {

    @Test
    fun test1() = runBlocking {
        val source = Channel<State>()
        val actions = Channel<Updater>()

        source.send(State())
/*
        scan(source, actions)

        actions.send(Increment())*/

        val receive = source.receive()
        println("result $receive")

    }

    @Test
    fun test2() = runBlocking {
        val produceStates = produceStates()

        consumeStates()
    }

    fun CoroutineScope.produceStates() = produce<State> {
        for (i in 1..10) {
            this.send(State(i))
        }
    }

    fun CoroutineScope.consumeStates() = produce<State> {
        for (i in 1..10) {
            this.send(State(i))
        }
    }



    private fun CoroutineScope.scan(stateChannel: Channel<State>, actionsChannel: Channel<Updater>) = produce<State> {
        for (action in actionsChannel) {
            val lastState = stateChannel.receive()
            stateChannel.send(action.update(lastState))
        }

    }
}
