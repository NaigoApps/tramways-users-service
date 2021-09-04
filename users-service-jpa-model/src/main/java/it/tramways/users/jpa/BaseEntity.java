package it.tramways.users.jpa;

import it.tramways.core.Identifiable;
import java.util.UUID;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Identifiable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String uuid;

	public BaseEntity() {
		uuid = UUID.randomUUID().toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
