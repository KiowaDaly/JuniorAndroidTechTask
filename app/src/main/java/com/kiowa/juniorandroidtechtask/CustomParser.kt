package com.kiowa.juniorandroidtechtask

import java.text.DecimalFormat

class CustomParser {
    fun parsePopulation(pop:Long):String{
        var limits = arrayOf(100,1000,1000000,1000000000)
        var result : String
        var df : DecimalFormat = DecimalFormat("#")
        df.maximumFractionDigits=8
        result = when{
            pop >= limits[3] -> "${df.format(pop.toDouble()/limits[3])} Billion"
            pop >= limits[2] && pop<limits[3] -> "${df.format(pop.toDouble()/limits[2])} Million"
            pop >= limits[1]  && pop<limits[2]-> "${df.format(pop.toDouble()/limits[1])} Thousand"
            else -> ""+pop.toDouble()
        }

        return result
    }
    fun parseArea(area:Double):String{

        return "${area}km\u00B2"
    }
}