package com.task.imgurgalleryapp.ui.gallery

import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.*
import com.task.imgurgalleryapp.R
import com.task.imgurgalleryapp.data.model.GalleryModel
import com.task.imgurgalleryapp.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import android.widget.ArrayAdapter
import com.task.imgurgalleryapp.databinding.FragmentGalleryBinding
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuInflater
import androidx.navigation.fragment.findNavController


/**
 * Created by Abuzar on 11/11/2019.
 */
class GalleryFragment : BaseFragment<FragmentGalleryBinding>(),
    GalleryNavigator {

    private lateinit var adapter: GalleryAdapter
    private val galleryViewModel: GalleryViewModel by viewModel()

    override fun getLayoutId(): Int {
        return R.layout.fragment_gallery
    }

    override fun getViewModel(): ViewModel {
        return galleryViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
        setHasOptionsMenu(true)
        adapter = GalleryAdapter(this)

        setLinearLayoutManager()
        getBinding().list.adapter = adapter
        val viewTypeOptions = resources.getStringArray(R.array.viewType)
        getBinding().viewTypeSpinner.adapter = ArrayAdapter<String>(
            activity!!,
            android.R.layout.simple_spinner_dropdown_item,
            viewTypeOptions
        )
        galleryViewModel.setNavigator(this)
        if (savedInstanceState == null) {
            galleryViewModel.getGalleryApi()
        } else {
            setGalleryList(galleryViewModel.galleryList)
        }
    }

    override fun setLinearLayoutManager() {
        getBinding().list.layoutManager = LinearLayoutManager(context)

    }

    override fun setGridLayoutManager() {
        getBinding().list.layoutManager = GridLayoutManager(context, 2)

    }

    override fun setStaggeredLayoutManager() {
        getBinding().list.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
    }

    override fun setGalleryList(t: List<GalleryModel>) {
        adapter.submitList(t)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                launchAboutFragment()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun launchAboutFragment() {
        findNavController().navigate(R.id.aboutFragment)
    }

}
