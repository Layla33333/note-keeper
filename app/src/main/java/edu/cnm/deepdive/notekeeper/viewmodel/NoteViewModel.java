package edu.cnm.deepdive.notekeeper.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.notekeeper.model.entity.Note;
import edu.cnm.deepdive.notekeeper.service.NoteRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class NoteViewModel
    extends AndroidViewModel
    implements LifecycleObserver {

  private final NoteRepository repository;
  private final MutableLiveData<Throwable> throwable;
  private final MutableLiveData<Long> noteId;
  private final LiveData<Note> note;
  private final CompositeDisposable pending;


  public NoteViewModel(@NonNull Application application) {
    super(application);
    repository = new NoteRepository(application);
    throwable = new MutableLiveData<>();
    noteId = new MutableLiveData<>();
    note = Transformations.switchMap(noteId, repository::get);
    pending = new CompositeDisposable();
  }

  public MutableLiveData<Throwable> getThrowable() {
    return throwable;
  }

  public LiveData<Note> getNote() {
    return note;
  }
  public void setNoteId(long id) {
    noteId.setValue(id);
  }

  public LiveData<List<Note>> getNotes() {
    return repository.getAll();
  }

  public void save(Note note) {
    pending.add(
        repository
            .save(note)
            .subscribe(
                (savedNote) -> {},
                this::postThrowable
            )
    );
  }
  public void delete(Note note) {
    pending.add(
        repository
            .delete(note)
            .subscribe(
                () -> {},
                this::postThrowable
            )
    );
  }
  private void postThrowable(Throwable throwable) {
    Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);
  }

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }
}

