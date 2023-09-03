package pro.developer.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import android.widget.TextView

class About_it : AppCompatActivity() {

    lateinit var note: Note
    lateinit var scrol:ScrollView
    lateinit var txt_desc:TextView
    lateinit var txt_topic:TextView
    var noteID = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_it)

        scrol=findViewById(R.id.scrollview)
        txt_topic=findViewById(R.id.txttopic)
        txt_desc=findViewById(R.id.txtmatn)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title="Description"

        val  noteType = intent.getStringExtra("noteType")
        if(noteType.equals("Edit")){
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteID",-1)

            txt_topic.setText(noteTitle)
            txt_desc.setText(noteDesc)


        }else{



        }




    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }
}