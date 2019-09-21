package com.flogiston.test.presentation.autocomplete


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel


import com.flogiston.test.R
import com.flogiston.test.databinding.FragmentAutocomleteBinding
import com.flogiston.test.presentation.autocomplete.recyclerView.AutocompleteAdapter
import kotlinx.android.synthetic.main.fragment_autocomlete.*

/**
 * A simple [Fragment] subclass.
 */
class AutocompleteFragment : Fragment() {

    private val viewModel: AutocompleteViewModel by viewModel()
    private val autocompleteAdapter = AutocompleteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAutocomleteBinding>(
            inflater,
            R.layout.fragment_autocomlete,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.autocompleteValues = viewModel.autocompleteValues
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        suggestsRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = autocompleteAdapter
        }
        viewModel.liveSuggests.observe(this, Observer {
            autocompleteAdapter.suggests = it
            autocompleteAdapter.notifyDataSetChanged()
        })
    }
}