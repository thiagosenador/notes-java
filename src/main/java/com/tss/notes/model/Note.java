package com.tss.notes.model;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class Note extends DatastoreEntity {

	public static final String KIND = "name";

	private String content;
	private Double latitude;
	private Double longitude;
	private String user;
	private Timestamp createDate;
	private String picture;

	@Override
	public FullEntity<IncompleteKey> toDatastore(IncompleteKey key) {
		return FullEntity.newBuilder(key).set("content", this.content).set("latitude", this.latitude)
				.set("longitude", this.longitude).set("user", this.user).set("createDate", this.createDate)
				.set("picture", this.picture).build();
	}

	public static Note fromDatastore(Entity entity) {
		return Note.builder().content(entity.getString("content")).latitude(entity.getDouble("latitude"))
				.longitude(entity.getDouble("longitude")).user(entity.getString("user"))
				.createDate(entity.getTimestamp("createDate")).picture(entity.getString("picture")).build();

	}
}
