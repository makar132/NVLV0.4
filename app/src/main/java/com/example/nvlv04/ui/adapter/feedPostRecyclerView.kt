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
import com.example.nvlv04.model.entity.feedPost

class feedPostRecyclerView: RecyclerView.Adapter<feedPostRecyclerView.UserViewHoldre>() {
    var feed:ArrayList<feedPost> = ArrayList()
    @SuppressLint("NotifyDataSetChanged")
    fun setList(postList: ArrayList<feedPost>){
        this.feed=postList
        notifyDataSetChanged()
    }
    class UserViewHoldre(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_postImage:ImageView=itemView.findViewById(R.id.iv_post_image)
        var tv_postDesribtion:TextView=itemView.findViewById(R.id.tv_post_describtion)
        fun bind(post: feedPost) {
            iv_postImage.setImageResource(R.drawable.custom_user_icon)
            tv_postDesribtion.text=post.description

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHoldre {
        var view:View =LayoutInflater.from(parent.context).inflate(R.layout.feed_post,parent,false)
        return UserViewHoldre(view)
    }

    override fun onBindViewHolder(holder: UserViewHoldre, position: Int) {
        var post:feedPost= feed[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return feed.size
    }
}