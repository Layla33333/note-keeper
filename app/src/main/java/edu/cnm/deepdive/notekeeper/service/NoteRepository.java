package edu.cnm.deepdive.notekeeper.service;

import android.app.Application;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.notekeeper.model.dao.NoteDao;
import edu.cnm.deepdive.notekeeper.model.entity.Note;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import java.util.List;

public class NoteRepository {

  //field
  private final Application context;
  private final NoteDao notedao;

  //constructor
  public NoteRepository(Application context) {
    this.context = context;
    notedao = NoteKeeperDatabase
        .getInstance()
        .getNoteDao();
  }

  public LiveData<Note> get(long noteId) {
    return notedao.select(noteId);
  }

  public LiveData<List<Note>> getAll() {
    return notedao.select();
  }

  public Single<Note> save(Note note) {
    Single<Note> task;
    note.setUpdated(new Date());
    if (note.getId() == 0) {
      note.setCreated(note.getUpdated());
      task = notedao
          .insert(note)
          .map((id) -> {
            note.setId(id);
            return note;
          });
    } else {
      task = notedao
          .update(note)
          .map((count) -> note);
    }
    return task.subscribeOn(Schedulers.io());
  }


  public Completable delete(Note note) {
    return (note.getId() == 0)
        ? Completable.complete()
        : notedao
    .delete(note)
        .ignoreElement()
            .subscribeOn(Schedulers.io());
  }
}
