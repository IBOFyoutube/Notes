package pro.developer.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDac {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes():LiveData<List<Note>>


}