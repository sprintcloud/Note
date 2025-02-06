package cn.ckai.note;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class NoteDetail extends Fragment {

    private NoteViewModel noteViewModel;
    private TextView titleTextView;
    private TextView contentTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_detail, container, false);

        titleTextView = view.findViewById(R.id.note_detail_title);
        contentTextView = view.findViewById(R.id.note_detail_content);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);

        noteViewModel.getSelectedNote().observe(getViewLifecycleOwner(), new Observer<Note>() {
            @Override
            public void onChanged(Note note) {
                if (note != null) {
                    titleTextView.setText(note.getTitle());
                    contentTextView.setText(note.getContent());
                }
            }

        });

        titleTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Note selectedNode = noteViewModel.getSelectedNote().getValue();
                if (selectedNode != null) {
                    selectedNode.setTitle(editable.toString());
                    noteViewModel.updateNote(selectedNode);
                }

            }
        });

        contentTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Note selectedNode = noteViewModel.getSelectedNote().getValue();
                if (selectedNode != null) {
                    selectedNode.setContent(editable.toString());
                    noteViewModel.updateNote(selectedNode);
                }

            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // 填充菜单资源
        inflater.inflate(R.menu.details_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.deleteNotes) {
            Note selectedNode = noteViewModel.getSelectedNote().getValue();
            if (selectedNode != null) {
                noteViewModel.deleteNote(selectedNode);
                Toast.makeText(requireActivity(), "Delete this Note", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(NoteDetail.this).navigateUp();

            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}