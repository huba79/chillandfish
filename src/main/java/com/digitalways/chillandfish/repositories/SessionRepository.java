package com.digitalways.chillandfish.repositories;

import com.digitalways.chillandfish.persistence.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

}
