package com.flogiston.test.presentation.autocomplete.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.flogiston.test.R
import kotlinx.android.synthetic.main.autocomplete_item.view.*
import kotlinx.android.synthetic.main.fragment_autocomlete.view.*

class AutocompleteAdapter : RecyclerView.Adapter<AutocompleteAdapter.AutocompleteViewHolder>() {
    lateinit var suggestClicked : (address : String) -> Unit
    var suggests = listOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutocompleteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.autocomplete_item,
            parent,
            false)
        return AutocompleteViewHolder(view)
    }

    override fun getItemCount() = suggests.size

    override fun onBindViewHolder(holder: AutocompleteViewHolder, position: Int) {
        holder.address.text = suggests[position]
    }

    inner class AutocompleteViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val address = view.suggest
        init {
            view.setOnClickListener {
                suggestClicked(it.suggest.text.toString())
            }
        }
    }
}