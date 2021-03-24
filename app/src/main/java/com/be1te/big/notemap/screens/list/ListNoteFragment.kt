package com.be1te.big.notemap.screens.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.be1te.big.notemap.R
import com.be1te.big.notemap.databinding.FragmentListNoteBinding
import com.be1te.big.notemap.databinding.FragmentStartBinding
import com.be1te.big.notemap.db.room.Note
import com.be1te.big.notemap.screens.start.StartFragmentViewModel
import com.be1te.big.notemap.utilits.APP_ACTIVITY
import com.be1te.big.notemap.utilits.TYPE_ROOM
import kotlinx.android.synthetic.main.fragment_list_note.*
import kotlinx.android.synthetic.main.fragment_start.*

class ListNoteFragment : Fragment() {

    private var _binding: FragmentListNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: ListNoteFragmentViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: NoteAdapter
    private lateinit var mObserverList: Observer<List<Note>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListNoteBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mAdapter = NoteAdapter()
        mRecyclerView = mBinding.rvList
        mRecyclerView.adapter = mAdapter
        mObserverList = Observer {
            val list = it.asReversed()
            mAdapter.setList(list)
        }
        mViewModel = ViewModelProvider(this).get(ListNoteFragmentViewModel::class.java)
        mViewModel.allNotes.observe(this, mObserverList)
        new_note.setOnClickListener {
            APP_ACTIVITY.mNavController.navigate(R.id.action_listNoteFragment_to_addNoteFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        mViewModel.allNotes.removeObserver(mObserverList)
        mRecyclerView.adapter = null
    }
}