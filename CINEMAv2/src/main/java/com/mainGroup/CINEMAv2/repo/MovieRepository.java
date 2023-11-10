package com.mainGroup.CINEMAv2.repo;

import com.mainGroup.CINEMAv2.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}