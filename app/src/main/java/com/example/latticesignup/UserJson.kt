package com.example.latticesignup

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER_DATA")
data class UserJson(
    @PrimaryKey

    @ColumnInfo(name = "data")
    val userJsonData : String
)