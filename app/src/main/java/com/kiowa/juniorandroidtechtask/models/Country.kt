package com.kiowa.juniorandroidtechtask.models

data class Country(var name:String,var capital:String,var region:String,var area:Double,var population : Long,var latlng:Array<Double>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Country

        if (name != other.name) return false
        if (capital != other.capital) return false
        if (region != other.region) return false
        if (area != other.area) return false
        if (population != other.population) return false
        if (!latlng.contentEquals(other.latlng)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + capital.hashCode()
        result = 31 * result + region.hashCode()
        result = 31 * result + area.hashCode()
        result = 31 * result + population.hashCode()
        result = 31 * result + latlng.contentHashCode()
        return result
    }

}