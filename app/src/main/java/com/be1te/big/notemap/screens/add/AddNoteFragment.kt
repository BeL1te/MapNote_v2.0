package com.be1te.big.notemap.screens.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.be1te.big.notemap.R
import com.be1te.big.notemap.databinding.FragmentAddNoteBinding
import com.be1te.big.notemap.db.room.Note
import com.be1te.big.notemap.screens.start.StartFragmentViewModel
import com.be1te.big.notemap.utilits.*
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.fragment_start.*


class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: AddNoteFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(AddNoteFragmentViewModel::class.java)
        add_note_button.setOnClickListener {
            val title = mBinding.editTitle.text.toString()
            val content = mBinding.editContent.text.toString()
            if (edit_title.text.isEmpty()) {
                doToast("Введите заголовок")
            } else {
                mViewModel.insert(Note(title = title, date = currentData(), coordinatesX = "0", coordinatesY = "0", content = content)) {
                    APP_ACTIVITY.mNavController.navigate(R.id.action_addNoteFragment_to_listNoteFragment)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}