package com.example.latticesignup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class UserAdapter internal constructor(context: Context) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var usersJson = emptyList<UserJson>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = inflater.inflate(R.layout.custom_user_single_row, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = usersJson[position].userJsonData
        val user = Gson().fromJson(current,User::class.java)

        holder.fullname.text = user.fullName
        holder.number.text = user.number
        holder.address.text = user.address
        holder.email.text = user.email
    }

    internal fun setWords(usersJson: List<UserJson>) {
        this.usersJson = usersJson
        notifyDataSetChanged()
    }

    inner class UserViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullname: TextView = itemView.findViewById(R.id.fullname_single_row)
        val number : TextView = itemView.findViewById(R.id.number_single_row)
        val address : TextView = itemView.findViewById(R.id.address_single_row)
        val email : TextView = itemView.findViewById(R.id.email_single_row)

    }


    override fun getItemCount() = usersJson.size
}