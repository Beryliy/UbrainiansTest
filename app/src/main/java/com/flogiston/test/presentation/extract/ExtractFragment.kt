package com.flogiston.test.presentation.extract


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.flogiston.test.R
import com.flogiston.test.databinding.FragmentExtractBinding

import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ExtractFragment : Fragment() {

    val viewModel : ExtractViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentExtractBinding>(
            inflater,
            R.layout.fragment_extract,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.extractValues = viewModel.extractValues
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        const val ZIP_ARCHIVE_URL = "zip_archive_url"
        const val SHOW_PROGRESS = "show_progress"
    }
}
