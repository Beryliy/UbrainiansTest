package com.flogiston.test.presentation.autocomplete.recyclerView

import androidx.recyclerview.widget.DiffUtil

class AddressDiffUtil (
    val oldList : List<String>,
    val newList : List<String>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].equals(newList[newItemPosition])

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int)  =
        oldList[oldItemPosition].equals(newList[newItemPosition])
}