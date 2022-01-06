package com.example.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ItemTodoBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {



    inner class TodoViewHolder(var binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<TodoDataClass>(){
        override fun areItemsTheSame(oldItem: TodoDataClass, newItem: TodoDataClass): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoDataClass, newItem: TodoDataClass): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this,diffCallback)
    var todos: List<TodoDataClass>
        get() {
            return differ.currentList
        }
        set(value) {differ.submitList(value)}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        val itemView = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),
        parent, false)

        return TodoViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            tvTextView.text = todo.title
        }


    }

    override fun getItemCount(): Int {
        return todos.size
    }
}