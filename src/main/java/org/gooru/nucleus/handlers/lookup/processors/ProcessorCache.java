package org.gooru.nucleus.handlers.lookup.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.json.JsonObject;

public final class ProcessorCache {
  private static final ProcessorCache INSTANCE = new ProcessorCache();
  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorCache.class);

  private final CacheHolder readingLevels;
  private final CacheHolder mediaFeatures;
  private final CacheHolder grades;
  private final CacheHolder educationalUse;
  private final CacheHolder adStatus;
  private final CacheHolder cenSkills;
  private final CacheHolder accessHazards;
  private final CacheHolder momentsOfLearning;
  private final CacheHolder depthOfKnowledge;
  private final CacheHolder audience;
  private final CacheHolder licenses;
  private final CacheHolder languages;

  private ProcessorCache() {
    this.readingLevels = new CacheHolder();
    this.mediaFeatures = new CacheHolder();
    this.grades = new CacheHolder();
    this.educationalUse = new CacheHolder();
    this.adStatus = new CacheHolder();
    this.cenSkills = new CacheHolder();
    this.accessHazards = new CacheHolder();
    this.momentsOfLearning = new CacheHolder();
    this.audience = new CacheHolder();
    this.depthOfKnowledge = new CacheHolder();
    this.licenses = new CacheHolder();
    this.languages = new CacheHolder();
  }

  public static ProcessorCache getInstance() {
    return INSTANCE;
  }

  public JsonObject getReadingLevels() {
    return readingLevels.getCachedValue();
  }

  public void setReadingLevels(JsonObject readingLevels) {
    if (readingLevels != null) {
      LOGGER.debug("Trying to initialize reading levels");
      this.readingLevels.initialize(readingLevels);
    }
  }

  public JsonObject getMediaFeatures() {
    return this.mediaFeatures.getCachedValue();
  }

  public void setMediaFeatures(JsonObject mediaFeatures) {
    if (mediaFeatures != null) {
      LOGGER.debug("Trying to initialize media features");
      this.mediaFeatures.initialize(mediaFeatures);
    }
  }

  public JsonObject getGrades() {
    return this.grades.getCachedValue();
  }

  public void setGrades(JsonObject grades) {
    if (grades != null) {
      LOGGER.debug("Trying to initialize grades");
      this.grades.initialize(grades);
    }
  }

  public JsonObject getEducationalUse() {
    return this.educationalUse.getCachedValue();
  }

  public void setEducationalUse(JsonObject educationalUse) {
    if (educationalUse != null) {
      LOGGER.debug("Trying to initialize educational use");
      this.educationalUse.initialize(educationalUse);
    }
  }

  public JsonObject getAdStatus() {
    return this.adStatus.getCachedValue();
  }

  public void setAdStatus(JsonObject adStatus) {
    if (adStatus != null) {
      LOGGER.debug("Trying to initialize ad status");
      this.adStatus.initialize(adStatus);
    }
  }

  public JsonObject getCenSkills() {
    return this.cenSkills.getCachedValue();
  }

  public void setCenSkills(JsonObject cenSkills) {
    if (cenSkills != null) {
      LOGGER.debug("Trying to initialize 21st century skills");
      this.cenSkills.initialize(cenSkills);
    }
  }

  public JsonObject getAccessHazards() {
    return this.accessHazards.getCachedValue();
  }

  public void setAccessHazards(JsonObject accessHazards) {
    if (accessHazards != null) {
      LOGGER.debug("Trying to initialize access hazards");
      this.accessHazards.initialize(accessHazards);
    }
  }

  public JsonObject getMomentsOfLearning() {
    return this.momentsOfLearning.getCachedValue();
  }

  public void setMomentsOfLearning(JsonObject momentsOfLearning) {
    if (momentsOfLearning != null) {
      LOGGER.debug("Trying to initialize moments of learning");
      this.momentsOfLearning.initialize(momentsOfLearning);
    }
  }

  public JsonObject getDepthOfKnowledge() {
    return this.depthOfKnowledge.getCachedValue();
  }

  public void setDepthOfKnowledge(JsonObject depthOfKnowledge) {
    if (depthOfKnowledge != null) {
      LOGGER.debug("Trying to initialize depth of knowledge");
      this.depthOfKnowledge.initialize(depthOfKnowledge);
    }
  }

  public JsonObject getAudience() {
    return this.audience.getCachedValue();
  }

  public void setAudience(JsonObject audience) {
    if (audience != null) {
      LOGGER.debug("Trying to initialize audience");
      this.audience.initialize(audience);
    }
  }

  public JsonObject getLicenses() {
    return this.licenses.getCachedValue();
  }

  public void setLicenses(JsonObject licenses) {
    if (licenses != null) {
      LOGGER.debug("Trying to initialize licenses");
      this.licenses.initialize(licenses);
    }
  }

  public JsonObject getLanguages() {
    return this.languages.getCachedValue();
  }

  public void setLanguages(JsonObject languages) {
    if (languages != null) {
      LOGGER.debug("Trying to initialize languages");
      this.languages.initialize(languages);
    }
  }

  private static class CacheHolder {
    private final Object lock = new Object();
    private JsonObject cache;
    private volatile boolean initialized = false;

    public CacheHolder() {}

    public void initialize(JsonObject cacheItem) {
      if (cacheItem != null) {
        if (!initialized) {
          synchronized (lock) {
            if (!initialized) {
              LOGGER.debug("Initialization successful");
              this.cache = cacheItem.copy();
              this.initialized = true;
            }
          }
        }
      }
    }

    public JsonObject getCachedValue() {
      if (this.cache == null) {
        return null;
      }
      return this.cache.copy();
    }

  }
}
