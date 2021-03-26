package com.be1te.big.notemap.screens.add

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.be1te.big.notemap.R
import com.be1te.big.notemap.databinding.FragmentAddNoteBinding
import com.be1te.big.notemap.db.room.Note
import com.be1te.big.notemap.utilits.*
import kotlinx.android.synthetic.main.fragment_add_note.*


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
        setHasOptionsMenu(true)
        mViewModel = ViewModelProvider(this).get(AddNoteFragmentViewModel::class.java)
        mViewModel.setTitle()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val title = mBinding.editTitle.text.toString()
        val content = mBinding.editContent.text.toString()
        when (item.itemId) {
            R.id.btn_add -> if (mBinding.editTitle.text.isEmpty()) doToast("Enter title") else mViewModel.insert(
                Note(
                    title = title,
                    date = currentData(),
                    coordinatesX = "0",
                    coordinatesY = "0",
                    content = content
                )
            ) {
                APP_ACTIVITY.navController.navigate(R.id.action_addNoteFragment_to_listNoteFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        super.onStop()
        if (mBinding.editTitle.text.isNotEmpty()) {
            val title = mBinding.editTitle.text.toString()
            val content = mBinding.editContent.text.toString()
            mViewModel.insert(Note(
                title = title,
                date = currentData(),
                coordinatesX = "0",
                coordinatesY = "0",
                content = content)) { doToast("Note saved") }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}