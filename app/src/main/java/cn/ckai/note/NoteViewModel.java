package cn.ckai.note;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private LiveData<List<Note>> allNotes;
    private NoteRepositorio noteRepositorio;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepositorio = new NoteRepositorio(application);
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insertNote(final Note note) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                noteRepositorio.insertNote(note);
            }
        }).start();
    }
}
