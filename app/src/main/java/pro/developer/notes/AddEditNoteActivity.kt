package pro.developer.notes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {

    lateinit var noteTitleEdit: EditText
    lateinit var noteDescriptionEdit: EditText
    lateinit var addUpdateBtn: Button
    lateinit var viewModel: NoteViewModel
    var noteID = -1


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        noteTitleEdit = findViewById(R.id.idEditNoteTitle)
        noteDescriptionEdit = findViewById(R.id.editNoteDescription)
        addUpdateBtn = findViewById(R.id.idBtnAddUpdate)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title="Notes"


        viewModel= ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        val  noteType = intent.getStringExtra("noteType")
        if(noteType.equals("Edit")){
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteID",-1)
            addUpdateBtn.setText("Note Update")

            noteTitleEdit.setText(noteTitle)
            noteDescriptionEdit.setText(noteDesc)


        }else{

            addUpdateBtn.setText("Save Note..")


        }

        addUpdateBtn.setOnClickListener{
            val noteTitle=noteTitleEdit.text.toString()
            val noteDescription=noteDescriptionEdit.text.toString()

            if(noteType.equals("Edit")){
                if(noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String=sdf.format(Date())
                    val updateNote = Note(noteTitle,noteDescription,currentDate)
                    updateNote.id=noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText( this,"Note Updated..",Toast.LENGTH_LONG).show()


                }
            }else{
                if(noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String=sdf.format(Date())
                    viewModel.addNote(Note(noteTitle,noteDescription,currentDate))
                    Toast.makeText( this,"Note Added..",Toast.LENGTH_LONG).show()


                }

            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }




}