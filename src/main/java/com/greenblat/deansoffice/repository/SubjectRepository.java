package com.greenblat.deansoffice.repository;

import com.greenblat.deansoffice.model.Subject;

import java.util.Optional;

public interface SubjectRepository {

    Optional<Subject> findByName(String name);
}
