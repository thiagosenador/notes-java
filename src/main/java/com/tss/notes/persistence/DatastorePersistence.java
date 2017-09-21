package com.tss.notes.persistence;

import com.tss.notes.model.Note;

public interface DatastorePersistence {

	public Note createNote(Note note);
}
