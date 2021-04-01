package com.example.nouste.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nouste.R
import com.example.nouste.adapters.listeners.OnGradientClickListener
import com.example.nouste.utils.Gradients
import com.example.nouste.utils.setImageGradient
import com.makeramen.roundedimageview.RoundedImageView

class GradientAdapter(private val onGradientClickListener: OnGradientClickListener) :
    RecyclerView.Adapter<GradientAdapter.GradientViewHolder>() {

    private val gradientsList = arrayListOf<Gradients>()

    inner class GradientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val gradientButton = itemView.findViewById<RoundedImageView>(R.id.btnSelectGradient)

        fun bind(position: Int) {
            gradientButton.setImageGradient(gradient = gradientsList[position])
            itemView.setOnClickListener {
                onGradientClickListener.onClick(gradient = gradientsList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GradientViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.gradient_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: GradientViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount() = gradientsList.count()

    fun addGradients(gradients: Array<Gradients>) = gradientsList.addAll(gradients)

}