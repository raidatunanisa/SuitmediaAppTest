package com.suitmedia.test

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suitmedia.test.databinding.ItemUserBinding

class UsersAdapter: PagingDataAdapter<DataItem, UsersAdapter.UsersViewHolder>(
    DIFF_CALLBACK)  {

    override fun onBindViewHolder(holder: UsersAdapter.UsersViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.UsersViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    inner class UsersViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(listUsers: DataItem){
            binding.apply {
                tvFirstname.text = listUsers.firstName
                tvLastname.text = listUsers.lastName
                tvEmail.text = listUsers.email
                Glide.with(circleImageView)
                    .load(listUsers.avatar)
                    .into(circleImageView)
                itemView.setOnClickListener {
                    val fullName = "${listUsers.firstName} ${listUsers.lastName}"
                    val intent = Intent(itemView.context, SecondActivity::class.java)
                    intent.putExtra(EXTRA_ID, fullName)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }

    companion object {
        const val EXTRA_ID = "extra_id"

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}