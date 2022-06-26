package com.example.nvlv04.ui.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nvlv04.R
import com.example.nvlv04.model.entity.User

class UserRecyclerView: RecyclerView.Adapter<UserRecyclerView.UserViewHoldre>() {
    var userList:List<User> = emptyList()
    @SuppressLint("NotifyDataSetChanged")
    fun setList(userList: List<User>){
        this.userList=userList
        notifyDataSetChanged()
    }
    class UserViewHoldre(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_userImage:ImageView=itemView.findViewById(R.id.iv_userimage)
        var iv_userName:TextView=itemView.findViewById(R.id.tv_username)
        var iv_userMessage:TextView=itemView.findViewById(R.id.tv_message)
        fun bind(user: User){
            iv_userImage.setImageResource(R.drawable.custom_user_icon)
            iv_userName.text=user.name
            iv_userMessage.text=user.message

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHoldre {
        var view:View =LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false)
        return UserViewHoldre(view)
    }

    override fun onBindViewHolder(holder: UserViewHoldre, position: Int) {
        var user:User=userList.get(position)
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}