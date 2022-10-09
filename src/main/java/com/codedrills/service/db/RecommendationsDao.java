package com.codedrills.service.db;

import com.codedrills.model.recommendation.Recommendation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationsDao extends CrudRepository<Recommendation, String> {
  Recommendation save(Recommendation recommendation);

  Recommendation findOne(String id);

  boolean exists(String id);
}
