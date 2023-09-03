package pro.developer.notes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRvAdapter(
    val context: MainActivity,
    val noteClickInterface: MainActivity,
    val noteClickDeleteInterface: MainActivity
):RecyclerView.Adapter<NoteRvAdapter.ViewHolder>(){

    private val allNotes=ArrayList<Note>()
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val noteTv = itemView.findViewById<TextView>(R.id.idTvNoteTitle)
        val timeTv = itemView.findViewById<TextView>(R.id.idTvTimeStamp)
        val deleteTv = itemView.findViewById<ImageView>(R.id.idTvDelete)
        val editTv = itemView.findViewById<ImageView>(R.id.idTvEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item,parent,false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTv.setText(allNotes.get(position).noteTitle)
        holder.timeTv.setText("Last Update : "+allNotes.get(position).timeStamp)

        holder.deleteTv.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }
        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes.get(position))
        }
        holder.editTv.setOnClickListener {
            noteClickInterface.onEditC(allNotes.get(position))
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note: Note)
    fun onEditC(note: Note)
}

interface NoteClickInterface {
    fun onNoteClick(note: Note)
}