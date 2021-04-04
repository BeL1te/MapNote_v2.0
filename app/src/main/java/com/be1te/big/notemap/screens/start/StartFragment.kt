package com.be1te.big.notemap.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.be1te.big.notemap.R
import com.be1te.big.notemap.databinding.FragmentStartBinding
import com.be1te.big.notemap.utilits.*


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
        mBinding.btnRoom.setOnClickListener {
            mViewModel.initDatabase(TYPE_ROOM) {
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_listNoteFragment)
            }
        }

        mBinding.btnFirebase.setOnClickListener {
            mBinding.fourthCardView.isVisible = true
            mBinding.enterEmail.isVisible = true
            mBinding.enterPassword.isVisible = true
            mBinding.thirdCardView.isVisible = false
            mBinding.secondCardView.isVisible = false
            mBinding.mainStartText.text = getString(R.string.enter_the_data)

            mBinding.btnLoginFirebase.setOnClickListener {
                val emailInput = mBinding.enterEmail.text.toString()
                val passwordInput = mBinding.enterPassword.text.toString()

                if (emailInput.isNotEmpty() && passwordInput.isNotEmpty()) {
                    EMAIL = mBinding.enterEmail.text.toString()
                    PASSWORD = mBinding.enterPassword.text.toString()
                    mViewModel.initDatabase(TYPE_FIREBASE) {
                        APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_listNoteFragment)
                    }
                } else {
                    doToast("Enter e-mail and password")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}