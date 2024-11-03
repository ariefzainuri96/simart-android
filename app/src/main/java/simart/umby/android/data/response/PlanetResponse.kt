package simart.umby.android.data.response

import com.google.gson.annotations.SerializedName

data class PlanetResponse(
    val count: Int?,
    val next: Any?,
    val previous: String?,
    val results: List<Result?>?
)

data class Result(
    val climate: String?,
    val created: String?,
    val diameter: String?,
    val edited: String?,
    val films: List<Any?>?,
    val gravity: String?,
    val name: String?,
    @SerializedName("orbital_period")
    val orbitalPeriod: String?,
    val population: String?,
    val residents: List<String?>?,
    @SerializedName("rotation_period")
    val rotationPeriod: String?,
    @SerializedName("surface_water")
    val surfaceWater: String?,
    val terrain: String?,
    val url: String?
)