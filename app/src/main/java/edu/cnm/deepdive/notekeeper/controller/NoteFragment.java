package edu.cnm.deepdive.notekeeper.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import edu.cnm.deepdive.notekeeper.R;
import edu.cnm.deepdive.notekeeper.databinding.FragmentNoteBinding;
import edu.cnm.deepdive.notekeeper.model.entity.Note;

public class NoteFragment extends Fragment {

  private FragmentNoteBinding binding;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState
  ) {

    binding = FragmentNoteBinding.inflate(inflater, container, false);
    binding.addNote.setOnClickListener((v) -> Navigation
        .findNavController(binding.getRoot())
        .navigate(NoteFragmentDirections.openNote()));
    return binding.getRoot();

  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

}