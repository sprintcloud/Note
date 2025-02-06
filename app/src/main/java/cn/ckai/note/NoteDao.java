package cn.ckai.note;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insertNote(Note note);

    @Query("SELECT * FROM Note")
    LiveData<List<Note>> getAllNotes();

    @Query("DELETE FROM Note")
    void deleteAllNotes();

    @Delete
    void deleteNote(Note note);

    @Update
    void updateNote(Note note);
}
