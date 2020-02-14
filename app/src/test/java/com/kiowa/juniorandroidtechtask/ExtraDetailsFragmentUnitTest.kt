package com.kiowa.juniorandroidtechtask

import junit.framework.Assert.*
import org.junit.Test

class ExtraDetailsFragmentUnitTest {
    private val customParser = CustomParser()
    @Test
    fun popBelowThousandIsCorrect(){
        assertEquals("250.0",customParser.parsePopulation(250))
    }
    @Test
    fun popAboveMillionIsCorrect(){
        assertEquals("2.5 Million",customParser.parsePopulation(2500000))
        assertNotSame("2.5",customParser.parsePopulation(2500000))
    }
    @Test
    fun areaReturnsCorrectly(){
        assertNotSame("1100.0",customParser.parseArea(1100.0))
        assertTrue(customParser.parseArea(1101.0).contains("km"))
    }

}