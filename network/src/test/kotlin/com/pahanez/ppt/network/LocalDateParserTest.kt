package com.pahanez.ppt.network

import com.pahanez.ppt.network.utils.TimeParser
import org.junit.Assert
import org.junit.Test

class LocalDateParserTest {

    @Test fun testDateParsing() {
        //given
        val dateToParse = "2019-05-10 21:01:59"

        //when
        val res = TimeParser.parse(dateToParse)

        //then
        Assert.assertEquals(2019, res.year)
        Assert.assertEquals(5, res.monthValue)
        Assert.assertEquals(10, res.dayOfMonth)
        Assert.assertEquals(21, res.hour)
        Assert.assertEquals(1, res.minute)
        Assert.assertEquals(59, res.second)
    }
}