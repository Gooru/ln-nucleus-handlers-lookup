package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

/**
 * Created by ashish on 11/1/16.
 */
public final class DBHandlerBuilder {

  private DBHandlerBuilder() {
    throw new AssertionError();
  }

  public static DBHandler fetchRowlistExecutorHandlerBuilder(String name, String sql, String[] fieldsInJson) {
    return new FetchRowlistExecutorHandler(name, sql, fieldsInJson);
  }

  public static DBHandler schoolHandlerBuilder(String keyword, String schoolDistrictId) {
    return new SchoolHandler(keyword, schoolDistrictId);
  }

  public static DBHandler schoolDistrictHandlerBuilder(String keyword) {
    return new SchoolDistrictHandler(keyword);
  }

  public static DBHandler statesHandlerBuilder(String countryId, String keyword) {
    return new StatesHandler(countryId, keyword);
  }

  public static DBHandler countriesHandlerBuilder(String keyword) {
    return new CountriesHandler(keyword);
  }

  public static DBHandler build21CenSkillsHandlerBuilder() {
    return new Cen21SkillsHandler();
  }
}
