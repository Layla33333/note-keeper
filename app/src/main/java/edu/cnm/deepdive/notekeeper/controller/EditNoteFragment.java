package edu.cnm.deepdive.notekeeper.controller;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import edu.cnm.deepdive.notekeeper.R;
import edu.cnm.deepdive.notekeeper.databinding.FragmentEditNoteBinding;
import edu.cnm.deepdive.notekeeper.viewmodel.NoteViewModel;

public class EditNoteFragment extends BottomSheetDialogFragment {

  private FragmentEditNoteBinding binding;
  private NoteViewModel viewModel;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TODO Get any parameters values passed in.
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentEditNoteBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(NoteViewModel.class);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}