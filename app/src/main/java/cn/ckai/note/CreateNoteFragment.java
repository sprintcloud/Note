package cn.ckai.note;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateNoteFragment extends Fragment {
    private NoteViewModel noteViewModell;
    private EditText editTextTitle;
    private EditText editTextContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_note, container, false);

        editTextTitle = view.findViewById(R.id.editTextContent);
        editTextContent = view.findViewById(R.id.editTextContent);
        Button buttonSaveNote = view.findViewById(R.id.buttonSaveNote);

        noteViewModell = new ViewModelProvider(this).get(NoteViewModel.class);

        buttonSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextTitle.getText().toString().trim();
                String content = editTextContent.getText().toString().trim();

                if (!title.isEmpty() && !content.isEmpty()) {
                    Note note = new Note(title, content);
                    noteViewModell.insertNote(note);
                    NavHostFragment.findNavController(CreateNoteFragment.this).navigateUp();
                } else {
                    Toast.makeText(requireContext(), "Title and content cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}