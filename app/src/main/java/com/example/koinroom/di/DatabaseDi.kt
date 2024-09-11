package com.example.koinroom.di

import android.content.Context
import androidx.room.Room
import com.example.koinroom.db.NoteDatabase
import com.example.koinroom.utils.Constants.NOTE_DATABASE

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, NoteDatabase::class.java,
        NOTE_DATABASE)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun providerDao(db: NoteDatabase) = db.noteDao()