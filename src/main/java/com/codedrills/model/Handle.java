package com.codedrills.model;

import com.codedrills.util.HandleHelper;

public class Handle implements Comparable<Handle> {
  Site site;
  String handle;

  public Handle(Site site, String handle) {
    this.site = site;
    this.handle = handle.toLowerCase().trim();
  }

  public Site getSite() {
    return site;
  }

  public String getHandle() {
    return handle;
  }

  @Override
  public String toString() {
    return getSite().getShortName() + "/" + getHandle();
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;

    Handle handle1 = (Handle) o;

    if(getSite() != handle1.getSite()) return false;
    return getHandle().equals(handle1.getHandle());
  }

  @Override
  public int hashCode() {
    int result = getSite().hashCode();
    result = 31 * result + getHandle().hashCode();
    return result;
  }

  public String getProfileUrl() {
    return HandleHelper.buildProfileUrl(this);
  }

  @Override
  public int compareTo(Handle h) {
    if(equals(h)) return 0;
    if(site.equals(h.getSite())) return handle.compareTo(h.getHandle());
    return site.compareTo(h.getSite());
  }
}
