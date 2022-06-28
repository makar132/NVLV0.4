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
import com.example.nvlv04.model.entity.familyMember

class FamilyMemberRecyclerView: RecyclerView.Adapter<FamilyMemberRecyclerView.UserViewHoldre>() {
     var onListItemClick: OnListItemClick? = null
    var family:ArrayList<familyMember> = ArrayList()
    @SuppressLint("NotifyDataSetChanged")
    fun setList(familyList: ArrayList<familyMember>){
        this.family=familyList
        notifyDataSetChanged()
    }
    inner class UserViewHoldre(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_memberImage:ImageView=itemView.findViewById(R.id.iv_member_image)
        var tv_memberName:TextView=itemView.findViewById(R.id.tv_member_name)
        var tv_memberMedicalrecord:TextView=itemView.findViewById(R.id.tv_medical_record)
        fun bind(member: familyMember) {
            itemView.setOnClickListener{

                onListItemClick?.onItemClick(member)
            }
            iv_memberImage.setImageDrawable(member.photo.drawable)
            tv_memberName.text=member.firstname+" ,"+member.lastname
            tv_memberMedicalrecord.text=member.medical_history

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHoldre {
        var view:View =LayoutInflater.from(parent.context).inflate(R.layout.family_member_item,parent,false)
        return UserViewHoldre(view)
    }

    override fun onBindViewHolder(holder: UserViewHoldre, position: Int) {
        var member:familyMember= family[position]
        holder.bind(member)
    }

    override fun getItemCount(): Int {
        return family.size
    }
    fun getitemid(member:familyMember):Int{
        return family.indexOf(member)
    }

}