package com.example.srodenas.example_with_catalogs.data.users.database.converters

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateConverter {

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String?{
        return date?.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }



    @TypeConverter
    fun toLocalDate(date: String?) : LocalDate?{
        return date?.let{
            LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE)
        }
    }

}