package com.example.koinroom.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.koinroom.R
import com.example.koinroom.databinding.FragmentAddNoteBinding
import com.example.koinroom.db.NoteEntity
import com.example.koinroom.viewmodels.DatabaseViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject


class AddNoteFragment : BottomSheetDialogFragment() {

    private var _binding : FragmentAddNoteBinding?=null
    private val binding get() = _binding

    private val noteEntity by lazy { NoteEntity() }
    private val viewModel: DatabaseViewModel by inject()

    private var noteTitle = ""
    private var noteDesc = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddNoteBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        //setting the expanded state for our bottom sheet dialog
        (dialog as? BottomSheetDialog)?.behavior?.state = STATE_EXPANDED


        binding!!.apply {
            imgClose.setOnClickListener{
                dismiss()
            }

            btnSave.setOnClickListener {
                noteTitle = edtTitle.text.toString()
                noteDesc = edtDesc.text.toString()

                if (noteTitle.isEmpty()||noteDesc.isEmpty()){
                    Snackbar.make(it,"Title and Description can't be empty",Snackbar.LENGTH_SHORT).show()
                }else{
                    noteEntity.noteId=0
                    noteEntity.noteTitle=noteTitle
                    noteEntity.noteDesc=noteDesc

                    viewModel.saveNote(noteEntity)

                    edtTitle.setText("")//Clean edit text for title
                    edtDesc.setText("")//cleaning the edit text for description

                    dismiss()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}