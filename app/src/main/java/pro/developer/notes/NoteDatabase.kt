package pro.developer.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase :RoomDatabase(){

    abstract  fun getNotesDao():NotesDac

    companion object{

        @Volatile
        private var INTANCE:NoteDatabase?=null

        fun getDatabase(context:Context):NoteDatabase{
            return INTANCE ?: synchronized(this){
                val intance=Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INTANCE = intance
                intance
            }
        }
    }

}