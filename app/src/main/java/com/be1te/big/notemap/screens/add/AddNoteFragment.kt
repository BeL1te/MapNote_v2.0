package com.be1te.big.notemap.screens.add

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.be1te.big.notemap.R
import com.be1te.big.notemap.databinding.FragmentAddNoteBinding
import com.be1te.big.notemap.db.room.Note
import com.be1te.big.notemap.screens.map.MapFragment
import com.be1te.big.notemap.utilits.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback


class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: AddNoteFragmentViewModel
    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<View>
    private val fragment = MapFragment()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(layoutInflater)

        mBottomSheetBehavior = BottomSheetBehavior.from(mBinding.bottomSheet)

        parentFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()

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

        mBinding.arrowTop.setOnClickListener {
            if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
            if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        mBottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                mBinding.arrowTop.rotation = slideOffset * 180
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val title = mBinding.editTitle.text.toString()
        val content = mBinding.editContent.text.toString()
        fragment.setCoordinates()
        when (item.itemId) {
            R.id.btn_add -> if (mBinding.editTitle.text.isEmpty()) doToast("Enter title") else mViewModel.insert(
                Note(
                    title = title,
                    date = currentData(),
                    coordinatesX = COORDINATE_X,
                    coordinatesY = COORDINATE_Y,
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
            fragment.setCoordinates()
            mViewModel.insert(
                Note(
                    title = title,
                    date = currentData(),
                    coordinatesX = COORDINATE_X,
                    coordinatesY = COORDINATE_Y,
                    content = content
                )
            ) { doToast("Note saved") }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
