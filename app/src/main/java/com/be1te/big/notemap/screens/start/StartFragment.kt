package com.be1te.big.notemap.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.be1te.big.notemap.R
import com.be1te.big.notemap.databinding.FragmentStartBinding
import com.be1te.big.notemap.utilits.APP_ACTIVITY
import com.be1te.big.notemap.utilits.TYPE_ROOM
import kotlinx.android.synthetic.main.fragment_start.*


class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val mBinding get() = _binding!!
    lateinit var mViewModel: StartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)
        btn_room.setOnClickListener {
            mViewModel.initDatabase(TYPE_ROOM) {
                APP_ACTIVITY.mNavController.navigate(R.id.action_startFragment_to_listNoteFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}