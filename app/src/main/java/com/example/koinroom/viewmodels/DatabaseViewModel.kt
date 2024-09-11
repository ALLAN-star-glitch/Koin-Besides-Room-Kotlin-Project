package com.example.koinroom.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koinroom.db.NoteEntity
import com.example.koinroom.repository.DatabaseRepository
import kotlinx.coroutines.launch

class DatabaseViewModel(private val repository: DatabaseRepository) : ViewModel() {

    private val _noteList = MutableLiveData<List<NoteEntity>>()
    val noteList: LiveData<List<NoteEntity>>
            get() = _noteList

    fun getAllNotes() = viewModelScope.launch {
        repository.getAllNotes().collect{notes->
            _noteList.postValue(notes)
        }
    }

    fun saveNote(noteEntity: NoteEntity) = viewModelScope.launch {
        repository.saveNote(noteEntity)
    }
}