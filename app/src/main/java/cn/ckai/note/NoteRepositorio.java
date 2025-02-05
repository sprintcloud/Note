package cn.ckai.note;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepositorio {
    private NoteDao noteDao;

    public NoteRepositorio(Application application) {
        NoteDatabase database = NoteDatabase.getDatabase(application);
        noteDao = database.noteDao();
    }

    public LiveData<List<Note>> getAllNotes() {
        return noteDao.getAllNotes();
    }

    public void insertNote(final Note note) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                noteDao.insertNote(note);
            }
        }).start();
    }
}
