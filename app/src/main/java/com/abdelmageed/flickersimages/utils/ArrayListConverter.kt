package com.abdelmageed.flickersimages.utils

import androidx.room.TypeConverter
import com.abdelmageed.flickersimages.data.module.remote.dto.PhotoItem
import com.google.gson.Gson


class ArrayListConverter {
    @TypeConverter
    fun listToJsonString(value: List<PhotoItem>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) =
        Gson().fromJson(value, Array<PhotoItem>::class.java).toMutableList()
}