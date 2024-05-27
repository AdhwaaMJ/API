package com.project.api.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Movies")
data class Movie(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title")
    val title: String ,
    @ColumnInfo(name = "posterPath")
    val posterPath: String ,
    @ColumnInfo(name = "backdropPath")
    val backdropPath: String,
    @ColumnInfo(name = "page")
    val page: Int,
    @ColumnInfo(name = "overview")
    val overview: String
)