package com.codedrills.service.db;

import com.codedrills.model.Problem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemsDao extends CrudRepository<Problem, String> {
  @SuppressWarnings("unchecked")
  Problem save(Problem problem);

  List<Problem> findAll();
}
