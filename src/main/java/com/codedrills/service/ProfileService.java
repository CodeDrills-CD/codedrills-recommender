package com.codedrills.service;

import com.codedrills.model.Handle;
import com.codedrills.model.analysis.AnalysisContext;
import com.codedrills.model.analysis.AnalysisResult;
import com.codedrills.model.recommendation.PracticeRecommendations;
import com.codedrills.model.recommendation.RecommendationContext;
import com.codedrills.service.recommenders.RecommenderService;
import com.codedrills.util.HandleHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.codedrills.util.HandleHelper.canonicalHandles;

@Service
public class ProfileService {
  private static Logger logger = Logger.getLogger(ProfileService.class);

  @Autowired
  AnalysisService analysisService;
  @Autowired
  RecommenderService recommenderService;

  public AnalysisResult analyzeAndRecommend(String handlesQuery) {

    List<Handle> handles = HandleHelper.validateAndSplit(handlesQuery);

    logger.info(String.format("Analysis started for %s", handlesQuery));
    AnalysisContext analysisContext = analysisService.analyzeUser(handles);
    logger.info(String.format("Analysis completed for %s", handlesQuery));

    logger.info(String.format("Recommendations started for %s", handlesQuery));
    RecommendationContext recommendationContext = recommenderService.recommendations(analysisContext);
    PracticeRecommendations practiceRecommendations = new PracticeRecommendations(recommendationContext.getRecommendations());
    logger.info(String.format("Recommendations completed for %s", handlesQuery));

    return new AnalysisResult(canonicalHandles(handles), analysisContext.getUserStats(), practiceRecommendations);
  }

}
