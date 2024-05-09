package com.example.srodenas.example_with_catalogs.data.users.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "tbl_users")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int=0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "image") val imag: String
    )

@Entity(tableName = "tbl_alerts")
data class AlertEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "userid") val userId: Long, // Clave foránea que hace referencia al ID del usuario
    @ColumnInfo(name = "text_short") val textShort: String,
    @ColumnInfo(name = "message") val message: String,
    @ColumnInfo(name = "alertDate") val alertDate: LocalDate  //para almacenar la fecha

)