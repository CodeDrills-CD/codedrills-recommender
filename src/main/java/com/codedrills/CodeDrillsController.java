package com.codedrills;

import com.codedrills.model.analysis.AnalysisResult;
import com.codedrills.model.analysis.ComparatorResult;
import com.codedrills.model.exceptions.CodedrillsException;
import com.codedrills.model.recommendation.Recommendation;
import com.codedrills.service.ComparatorService;
import com.codedrills.service.ProfileService;
import com.codedrills.service.contests.ContestsService;
import com.codedrills.service.recommenders.RecommenderService;
import com.codedrills.util.HandleHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.TimeZone;

@Controller
public class CodeDrillsController {
  private static Logger logger = Logger.getLogger(CodeDrillsController.class);

  @Value("${is_prod}")
  private boolean isProd;

  @Autowired
  private ProfileService profileService;
  @Autowired
  private ContestsService contestsService;
  @Autowired
  private RecommenderService recommenderService;
  @Autowired
  private ComparatorService comparatorService;

  @SuppressWarnings("SameReturnValue")
  @ResponseBody
  @GetMapping("/status")
  public String status() {
    logger.info("Status request received");
    return "OK";
  }

  @GetMapping("/")
  public ModelAndView index() {
    logger.info("index request received");
    return recommenderView();
  }

  @GetMapping("/recommender")
  public ModelAndView recommender() {
    logger.info("recommender request received");
    return recommenderView();
  }

  @GetMapping("/tools/comparator")
  public ModelAndView comparator() {
    logger.info("comparator request received");
    ModelAndView modelAndView = newModelAndView();
    modelAndView.setViewName("comparator");
    modelAndView.addObject("handlesRegex", HandleHelper.HANDLES_REGEX);
    return modelAndView;
  }

  @GetMapping("/contests")
  public ModelAndView contests(TimeZone timeZone, @RequestParam(required = false) Integer utc) throws GeneralSecurityException, IOException {
    logger.info(String.format("contests request received with timezone %s, utc %s", timeZone, utc));
    ModelAndView modelAndView = newModelAndView();
    modelAndView.setViewName("contests");
    TimeZone useTZ;
    if(utc != null || timeZone == null) {
      useTZ = TimeZone.getTimeZone("UTC");
    } else {
      useTZ = timeZone;
      modelAndView.addObject("timezone", useTZ.getID());
    }

    modelAndView.addObject("contests", contestsService.fetchContests(useTZ));
    return modelAndView;
  }

  @GetMapping("/faq")
  public ModelAndView faq() {
    logger.info("faq request received");
    ModelAndView modelAndView = newModelAndView();
    modelAndView.setViewName("faq");
    return modelAndView;
  }

  @GetMapping("/about")
  public ModelAndView about() {
    logger.info("about request received");
    ModelAndView modelAndView = newModelAndView();
    modelAndView.setViewName("about");
    return modelAndView;
  }

  public ModelAndView recommenderView() {
    ModelAndView modelAndView = newModelAndView();
    modelAndView.addObject("handlesRegex", HandleHelper.HANDLES_REGEX);
    modelAndView.setViewName("recommender");
    return modelAndView;
  }

  private ModelAndView newModelAndView() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("isProd", isProd);
    return modelAndView;
  }

  @GetMapping("/profile")
  public ModelAndView profile(@RequestParam String handles) {
    logger.info(String.format("Analysis request received for %s", handles));

    ModelAndView modelAndView = newModelAndView();

    AnalysisResult result = profileService.analyzeAndRecommend(handles);

    modelAndView.setViewName("profile");
    logger.info(String.format("Analysis completed for %s", handles));
    modelAndView.addObject("result", result);

    return modelAndView;
  }


  @GetMapping("/tools/compare")
  public ModelAndView compare(@RequestParam String handlesA, @RequestParam String handlesB) {
    logger.info(String.format("Analysis request received for %s vs %s", handlesA, handlesB));

    ModelAndView modelAndView = newModelAndView();

    ComparatorResult comparatorResult = comparatorService.compare(handlesA, handlesB);

    modelAndView.setViewName("compare");
    logger.info(String.format("Comparision completed for %s vs %s", handlesA, handlesB));
    modelAndView.addObject("result", comparatorResult);

    return modelAndView;
  }

  @GetMapping("/recommendations/{id}")
  public ModelAndView recommendations(@PathVariable String id) {
    ModelAndView modelAndView = newModelAndView();
    Recommendation recommendation = recommenderService.getRecommendation(id);
    modelAndView.addObject("recommendation", recommendation);
    modelAndView.setViewName("recommendation_list");

    return modelAndView;
  }

  @GetMapping("/resources/tips")
  public ModelAndView tips() {
    ModelAndView modelAndView = newModelAndView();
    modelAndView.setViewName("content/tips");

    return modelAndView;
  }

  @GetMapping("/resources/tips/{tip}")
  public ModelAndView aTip(@PathVariable String tip) {
    ModelAndView modelAndView = newModelAndView();
    modelAndView.setViewName(String.format("content/tips/%s", tip));

    return modelAndView;
  }

  @ExceptionHandler(Exception.class)
  public ModelAndView handleError(HttpServletRequest req, Exception ex) {
    ModelAndView modelAndView = newModelAndView();
    modelAndView.setViewName("error");
    if(ex instanceof CodedrillsException) {
      modelAndView.addObject("errorText", ex.getMessage());
    } else {
      logger.error(String.format("Error while processing request %s", req.getRequestURI()), ex);
    }

    return modelAndView;
  }

}
