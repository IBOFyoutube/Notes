package pro.developer.notes

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDac: NotesDac) {

    val allNotes:LiveData<List<Note>> = notesDac.getAllNotes()

    suspend fun insert(note: Note){
        notesDac.insert(note)
    }
    suspend fun delete(note: Note){
        notesDac.delete(note)
    }
    suspend fun update(note: Note){
        notesDac.update(note)
    }

}