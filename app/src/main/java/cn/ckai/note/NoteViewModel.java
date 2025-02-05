package cn.ckai.note;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private LiveData<List<Note>> allNotes;
    private NoteRepositorio noteRepositorio;
    private MutableLiveData<Note> selectedNote = new MutableLiveData<>();


    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepositorio = new NoteRepositorio(application);
        allNotes = noteRepositorio.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insertNote(final Note note) {
        noteRepositorio.insertNote(note);
    }

    public void setSelectedNote(Note note) {
        selectedNote.setValue(note);
    }

    public MutableLiveData<Note> getSelectedNote() {
        return selectedNote;
    }
}
