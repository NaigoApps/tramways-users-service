package it.tramways.users.jpa;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserEntity.class)
public abstract class UserEntity_ extends it.tramways.jpa.BaseEntity_ {

	public static volatile SingularAttribute<UserEntity, String> password;
	public static volatile SetAttribute<UserEntity, RoleEntity> roles;
	public static volatile SingularAttribute<UserEntity, Boolean> enabled;
	public static volatile SingularAttribute<UserEntity, String> username;

	public static final String PASSWORD = "password";
	public static final String ROLES = "roles";
	public static final String ENABLED = "enabled";
	public static final String USERNAME = "username";

}

