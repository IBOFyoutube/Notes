package pro.developer.notes

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickDeleteInterface, NoteClickInterface {


    lateinit var notesRv:RecyclerView
    lateinit var addFab:FloatingActionButton
    lateinit var viewModal:NoteViewModel
    lateinit var note:Note


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesRv = findViewById(R.id.idRVNotes)
        addFab = findViewById(R.id.idFabaAddNote)



        val  noteRvAdapter = NoteRvAdapter(this,this,this)
        notesRv.setAdapter(noteRvAdapter)
        notesRv.layoutManager=LinearLayoutManager(this)

        // window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()



        viewModal=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModal.allNotes.observe(this, Observer { list->
            list?.let {
                noteRvAdapter.updateList(it)

            }
        })
        addFab.setOnClickListener {
            val intent=Intent(this,AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }


    override fun onDeleteIconClick(note: Note) {

        //val message:String?="${note.noteTitle}"
       // showCustomDialog(message)

       var builder=AlertDialog.Builder(this)
        builder.setTitle(" Delete ")
            .setMessage("Are you sure delete ${note.noteTitle} ?")

            .setCancelable(true)
            .setPositiveButton("Yes"){dialogInterface,it->

                viewModal.deleteNote(note)
                Toast.makeText(this, "${note.noteTitle} deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No"){dialogInterface,it->
                dialogInterface.cancel()
            }
            .show()



    }

    private fun showCustomDialog(message: String?) {

        val dialog=Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_delete)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvMassage:TextView=dialog.findViewById(R.id.tv_message_delete)
        val btnYes: Button =dialog.findViewById(R.id.btn_yes_answer)
        val btnNo:Button=dialog.findViewById(R.id.btn_no_answer)

        tvMassage.setText("$message")

        btnYes.setOnClickListener {

                viewModal.deleteNote(note)


        }
        btnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

    }

    override fun onNoteClick(note: Note) {

        val intent=Intent(this,About_it::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("noteID",note.id)
        startActivity(intent)


    }

    override fun onEditC(note: Note){

        val intent=Intent(this,AddEditNoteActivity::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("noteID",note.id)
        startActivity(intent)
        //this.finish()

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
        this.finish()
    }

}