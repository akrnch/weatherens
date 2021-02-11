package com.example.weatherens.data.local

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat

@Entity(tableName = "weather", indices = arrayOf(Index(value = ["lat", "lon"], unique = true)))
data class WeatherEntity (
    @PrimaryKey(autoGenerate = true) var weatherId: Int?,
    @ColumnInfo(name = "city") var city: String,
    @ColumnInfo(name = "epoch") var lastUpdateEpoch: Long,
    @ColumnInfo(name = "lat") val lat: Float,
    @ColumnInfo(name = "lon") val lon: Float,
    @ColumnInfo(name = "is_day") var isDay: Int,
    @ColumnInfo(name = "temp_C") var tempC: Float,
    @ColumnInfo(name = "temp_F") var tempF: Float,
    @ColumnInfo(name = "state_icon") var icon: String,
    @ColumnInfo(name = "state_desc") var description: String,
    @ColumnInfo(name = "wind_speed_kph") var windSpeedKph: Float,
    @ColumnInfo(name = "wind_speed_mph") var windSpeedMph: Float,
    @ColumnInfo(name = "precipitation_mm") var precipitationMm: Float,
    @ColumnInfo(name = "precipitation_in") var precipitationInches: Float,
    @ColumnInfo(name = "humidity") var humidityPercentage: Int
) : Parcelable {
    val cityToShow: String
        get() = city.capitalize()

    val descriptionToShow: String
        get() = description.capitalize()

    val datetimeToShow: String
        get() {
            val formatter = SimpleDateFormat("hh:mm a - EEEE, d MMM''yyyy")
            var parsed = formatter.format(lastUpdateEpoch * 1000)
            return parsed
                .replace("AM", "am")
                .replace("PM","pm")
                .replaceFirst("^0+(?!$)", "")
        }

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString() ?: "",
        parcel.readLong(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(weatherId)
        parcel.writeString(city)
        parcel.writeLong(lastUpdateEpoch)
        parcel.writeFloat(lat)
        parcel.writeFloat(lon)
        parcel.writeInt(isDay)
        parcel.writeFloat(tempC)
        parcel.writeFloat(tempF)
        parcel.writeString(icon)
        parcel.writeString(description)
        parcel.writeFloat(windSpeedKph)
        parcel.writeFloat(windSpeedMph)
        parcel.writeFloat(precipitationMm)
        parcel.writeFloat(precipitationInches)
        parcel.writeInt(humidityPercentage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherEntity> {
        override fun createFromParcel(parcel: Parcel): WeatherEntity {
            return WeatherEntity(parcel)
        }

        override fun newArray(size: Int): Array<WeatherEntity?> {
            return arrayOfNulls(size)
        }
    }
}