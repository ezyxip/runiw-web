package com.ezyxip.runiwweb.jpa;

import org.springframework.data.repository.CrudRepository;

public interface UsersCrud extends CrudRepository<User, Long> {
}
