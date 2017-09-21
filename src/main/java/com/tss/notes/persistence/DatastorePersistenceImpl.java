package com.tss.notes.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.tss.notes.model.Note;

@Repository
public class DatastorePersistenceImpl implements DatastorePersistence {

	private final Datastore datastore;

	public DatastorePersistenceImpl() {
		this.datastore = DatastoreOptions.getDefaultInstance().getService();
	}

	@Override
	public Note createNote(Note note) {

		IncompleteKey key = this.createKey(Note.KIND);
		FullEntity<IncompleteKey> noteEntity = note.toDatastore(key);

		this.datastore.add(noteEntity);

		return note;
	}

	public List<Note> getNotesFromUser(String user) {

		List<Note> notes = new ArrayList<Note>();

		Query<Entity> query = Query.newEntityQueryBuilder().setKind(Note.KIND)
				.setFilter(PropertyFilter.eq("user", user)).build();
		QueryResults<Entity> results = this.datastore.run(query);

		while (results.hasNext()) {
			Entity entity = results.next();
			notes.add(Note.fromDatastore(entity));
		}

		return notes;
	}

	public Entity getNote(Long id) {

		Key key = this.datastore.newKeyFactory().newKey(id);

		return this.datastore.get(key);
	}

	public void getNotesByLocation(Double latitude, Double longitude) {

	}

	private IncompleteKey createKey(String kind) {
		KeyFactory keyFactory = this.datastore.newKeyFactory().setKind(kind);
		return keyFactory.setKind(kind).newKey();
	}
}
