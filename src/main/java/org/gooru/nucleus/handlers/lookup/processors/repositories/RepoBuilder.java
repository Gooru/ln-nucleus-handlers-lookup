package org.gooru.nucleus.handlers.lookup.processors.repositories;

import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.ActiveJdbcRepoBuilder;

/**
 * Created by ashish on 29/12/15.
 */
public final class RepoBuilder {

  private RepoBuilder() {
    throw new AssertionError();
  }

  public static MetadataRepo buildMetadataRepo() {
    return ActiveJdbcRepoBuilder.buildMetadataRepo();
  }

  public static Cen21SkillsRepo buildCen21SkillsRepo() {
    return ActiveJdbcRepoBuilder.buildCen21SkillsRepo();
  }

  public static CountryRepo buildCountriesRepo() {
    return ActiveJdbcRepoBuilder.buildCountriesRepo();
  }

  public static StateRepo buildStatesRepo() {
    return ActiveJdbcRepoBuilder.buildStatesRepo();
  }

  public static SchoolDistrictRepo buildSchoolDistrictsRepo() {
    return ActiveJdbcRepoBuilder.buildSchoolDistrictsRepo();
  }

  public static SchoolRepo buildSchoolsRepo() {
    return ActiveJdbcRepoBuilder.buildSchoolsRepo();
  }

  public static LanguagesRepo buildLanguagesRepo() {
    return ActiveJdbcRepoBuilder.buildLanguagesRepo();
  }

}
