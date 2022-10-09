package com.codedrills.service.sites;

import com.codedrills.model.Problem;
import com.codedrills.model.Site;
import com.codedrills.model.Tag;
import com.codedrills.service.DataFetcher;
import com.codedrills.util.TagHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractSiteService implements SiteInterface {
  @Autowired
  protected DataFetcher dataFetcher;

  abstract public Site site();

  abstract public List<Problem> fetchProblems();

  protected List<Tag> mapTags(List<String> tagsStr) {
    List<Tag> tags = tagsStr.stream()
      .map(t -> TagHelper.mapTag(site(), t))
      .distinct()
      .filter(t -> !t.equals(Tag.UNTAGGED))
      .collect(Collectors.toList());

    if(tags.isEmpty()) tags.add(Tag.UNTAGGED);

    return tags;
  }

}
