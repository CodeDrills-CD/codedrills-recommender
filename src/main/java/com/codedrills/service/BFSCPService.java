package com.codedrills.service;

import com.codedrills.model.bfscp.Level;
import com.codedrills.model.bfscp.Module;
import com.codedrills.model.exceptions.InvalidInputException;
import com.codedrills.util.Helper;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class BFSCPService {
  private static Logger logger = Logger.getLogger(ProblemsService.class);
  @Value(value = "classpath:static/bfscp.json")
  private Resource modulesResource;
  private List<Level> levels;

  @PostConstruct
  private void loadJson() throws IOException {
    logger.info("Loading bfs cp json");
    InputStreamReader resourceAsStream = new InputStreamReader(modulesResource.getInputStream());

    levels = Helper.gson.fromJson(resourceAsStream, new TypeToken<List<Level>>() {
    }.getType());

    logger.info(String.format("Loaded %d levels", levels.size()));
    // TODO: add validation

    levels.stream()
      .forEach(Level::connect);
  }

  public List<Level> getLevels() {
    return levels;
  }

  public Module getModule(String id) {
    return levels
      .stream()
      .map(Level::getModules)
      .flatMap(List::stream)
      .filter(m -> m.getId().equals(id))
      .findFirst()
      .orElseThrow(() -> new InvalidInputException("Module id is wrong"));
  }
}
