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
import com.be1te.big.notemap.db.room.Note
import com.be1te.big.notemap.utilits.APP_ACTIVITY
import com.be1te.big.notemap.utilits.COORDINATE_X
import com.be1te.big.notemap.utilits.COORDINATE_Y
import kotlinx.android.synthetic.main.fragment_list_note.*

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
        mViewModel = ViewModelProvider(this).get(ListNoteFragmentViewModel::class.java)
        mViewModel.initializeCoordinates()
        mAdapter = NoteAdapter()
        mRecyclerView = mBinding.rvList
        mRecyclerView.adapter = mAdapter
        mObserverList = Observer {
            val list = it.asReversed()
            mAdapter.setList(list)
        }

        mViewModel.getTitle()
        mViewModel.allNotes.observe(this, mObserverList)
        new_note.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_listNoteFragment_to_addNoteFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mViewModel.allNotes.removeObserver(mObserverList)
        mRecyclerView.adapter = null
    }

    companion object {
        fun click(note: Note) {
            val bundle = Bundle()
            bundle.putSerializable("note", note)
            COORDINATE_X = note.coordinatesX
            COORDINATE_Y = note.coordinatesY
            APP_ACTIVITY.navController.navigate(
                R.id.action_listNoteFragment_to_noteFragment,
                bundle
            )
        }
    }
}