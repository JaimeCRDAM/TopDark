package com.example.topdark.vaderpages.fragments.ranking_pilots.adapter
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.topdark.R
import models.data_classes.idk.Data
import models.data_classes.to_present.Pilot

class Adapter(private val dataSet: MutableList<Data>, private val context: Context) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    companion object {
        var seleccionado:Int = -1
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item, context, position, this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_card,parent,false), this)
    }

    override fun getItemCount(): Int {

        return dataSet.size
    }

    class ViewHolder(view: View, var adapter: Adapter) : RecyclerView.ViewHolder(view) {

        val name = view.findViewById(R.id.TVICName) as TextView
        val age = view.findViewById(R.id.TVICAge) as TextView
        val experience = view.findViewById(R.id.TVICExperience) as TextView
        val stats = view.findViewById(R.id.TVICStats) as TextView
        val avatar = view.findViewById(R.id.IVICAvatar) as ImageView

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(pilot: Pilot, context: Context, pos: Int, adapter: Adapter){
            name.text = pilot.name
            age.text = pilot.age.toString()
            experience.text = pilot.experience.toString()
            stats.text = pilot.success.toString()
            avatar.setImageBitmap(pilot.avatar)


            if (pos == Adapter.seleccionado) {
                with(name) {
                    this.setTextColor(resources.getColor(R.color.purple_200))
                }
            }
            else {
                with(name) {
                    this.setTextColor(resources.getColor(R.color.black))
                }
            }
            /*itemView.setOnLongClickListener(View.OnLongClickListener {
                true
            })
            itemView.setOnClickListener(View.OnClickListener
            {
                if (pos == Adapter.seleccionado){
                    Adapter.seleccionado = -1
                }
                else {
                    Adapter.seleccionado = pos
                }
                miAdaptadorRecycler.notifyDataSetChanged()

                Toast.makeText(context, "Valor seleccionado " +  Adapter.seleccionado.toString(), Toast.LENGTH_SHORT).show()
            })*/
        }
    }
}