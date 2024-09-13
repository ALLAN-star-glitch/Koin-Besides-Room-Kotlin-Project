package com.example.koinroom.ui.activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koinroom.R
import com.example.koinroom.adapter.NoteAdapter
import com.example.koinroom.databinding.ActivityMainBinding
import com.example.koinroom.ui.fragments.AddNoteFragment
import com.example.koinroom.viewmodels.DatabaseViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding?=null
    private val binding get() = _binding

    private val noteAdapter by lazy {
        NoteAdapter()
    }

    private val viewModel: DatabaseViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding!!.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding?.apply {
            btnAddNote.setOnClickListener{
                AddNoteFragment().show(supportFragmentManager, AddNoteFragment().tag)

            }

            viewModel.getAllNotes()
            viewModel.noteList.observe(this@MainActivity){list->
                if (list.isNotEmpty()){
                    showEmpty(true)
                    noteAdapter.differ.submitList(list)
                    rvNoteList.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = noteAdapter
                    }
                }else{
                    showEmpty(false)
                }
            }
        }

    }

    private fun showEmpty(isShow: Boolean){
        binding?.apply {
            if (isShow) {
                rvNoteList.visibility = View.VISIBLE
                tvEmptyText.visibility = View.GONE
            }else{

                rvNoteList.visibility = View.GONE
                tvEmptyText.visibility = View.VISIBLE
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

}