package com.example.koinroom.repository

import com.example.koinroom.db.NoteDao
import com.example.koinroom.db.NoteEntity

class DatabaseRepository(private val dao: NoteDao) {

    suspend fun saveNote(noteEentity: NoteEntity) = dao.saveNote(noteEentity)
    suspend fun getAllNotes() = dao.getAllNotes()
}