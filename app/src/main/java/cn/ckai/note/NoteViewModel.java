package cn.ckai.note;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NoteViewModel extends ViewModel {
    private LiveData<List<Note>> allNotes;
    private NoteDao noteDao;

    public NoteViewModel(NoteDao noteDao) {
        this.noteDao = noteDao;
        allNotes = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
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
