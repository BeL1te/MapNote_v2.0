package com.be1te.big.notemap.screens.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.be1te.big.notemap.R
import com.be1te.big.notemap.databinding.FragmentNoteBinding
import com.be1te.big.notemap.db.room.Note
import com.be1te.big.notemap.utilits.APP_ACTIVITY

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: NoteFragmentViewModel
    private lateinit var mCurrentNote: Note

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        mCurrentNote = arguments?.getSerializable("note") as Note
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        mViewModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)
        mViewModel.setTitle(mCurrentNote.title)
        mBinding.dateText.text = mCurrentNote.date
        mBinding.contentText.text = mCurrentNote.content
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_delete -> mViewModel.delete(mCurrentNote) {
                APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_listNoteFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}