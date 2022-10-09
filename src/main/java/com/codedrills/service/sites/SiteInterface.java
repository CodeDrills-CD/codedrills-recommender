package com.codedrills.service.sites;

import com.codedrills.model.Handle;
import com.codedrills.model.Problem;
import com.codedrills.model.Site;
import com.codedrills.model.stats.UserStats;

import java.util.List;

public interface SiteInterface {
  Site site();

  List<Problem> fetchProblems();

  UserStats fetchUserStats(Handle handle);
}
