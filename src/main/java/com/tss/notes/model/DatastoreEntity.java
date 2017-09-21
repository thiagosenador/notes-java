package com.tss.notes.model;

import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;

import lombok.Data;

@Data
public abstract class DatastoreEntity {

	public Long id;

	public abstract FullEntity<IncompleteKey> toDatastore(IncompleteKey key);
}
