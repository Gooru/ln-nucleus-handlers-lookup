package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc;

import org.gooru.nucleus.handlers.lookup.processors.repositories.Cen21SkillsRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.CountryRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.MetadataRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.SchoolDistrictRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.SchoolRepo;
import org.gooru.nucleus.handlers.lookup.processors.repositories.StateRepo;

/**
 * Created by ashish on 29/12/15.
 */
public final class ActiveJdbcRepoBuilder {

  private ActiveJdbcRepoBuilder() {
    throw new AssertionError();
  }

  public static MetadataRepo buildMetadataRepo() {
    return new AJMetadataRepo();
  }

  public static Cen21SkillsRepo buildCen21SkillsRepo() {
    return new AJCen21SkillsRepo();
  }
  

  public static CountryRepo buildCountriesRepo() {
    return new AJCountryRepo();
  }
  
  public static StateRepo buildStatesRepo() {
    return new AJStateRepo();
  }
  
  public static SchoolDistrictRepo buildSchoolDistrictsRepo() {
    return new AJSchoolDistrictRepo();
  }
  
  public static SchoolRepo buildSchoolsRepo() {
    return new AJSchoolRepo();
  }
}
