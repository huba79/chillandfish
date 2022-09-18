package com.digitalways.chillandfish.authentication.repositories;

import com.digitalways.chillandfish.authentication.persistence.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    Session save(Session s);
}
