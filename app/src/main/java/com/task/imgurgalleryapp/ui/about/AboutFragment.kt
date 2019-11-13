package com.task.imgurgalleryapp.ui.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.task.imgurgalleryapp.R
import com.task.imgurgalleryapp.base.BaseFragment
import com.task.imgurgalleryapp.databinding.FragmentAboutBinding
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Abuzar on 11/12/2019.
 */
class AboutFragment : BaseFragment<FragmentAboutBinding>() {

    private val aboutViewModel: AboutViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.about)
        setHasOptionsMenu(false)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_about
    }

    override fun getViewModel(): ViewModel {
        return aboutViewModel
    }

}