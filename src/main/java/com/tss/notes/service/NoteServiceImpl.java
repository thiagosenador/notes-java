package com.tss.notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.notes.model.Note;
import com.tss.notes.persistence.DatastorePersistence;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private DatastorePersistence datastore;

	@Override
	public Note createNote(Note note) {

		return this.datastore.createNote(note);
	}

	public void setDatastore(DatastorePersistence datastore) {
		this.datastore = datastore;
	}
}
