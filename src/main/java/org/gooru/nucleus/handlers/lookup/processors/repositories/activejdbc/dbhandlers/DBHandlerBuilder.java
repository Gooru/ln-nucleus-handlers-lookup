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

  public static DBHandler schoolHandlerBuilder(String keyword, String schoolDistrictId, int limit, int offset) {
    return new SchoolHandler(keyword, schoolDistrictId, limit, offset);
  }

  public static DBHandler schoolDistrictHandlerBuilder(String keyword, String stateId, int limit, int offset) {
    return new SchoolDistrictHandler(keyword, stateId, limit , offset);
  }

  public static DBHandler statesHandlerBuilder(String countryId, String keyword, int limit, int offset) {
    return new StatesHandler(countryId, keyword, limit, offset);
  }

  public static DBHandler countriesHandlerBuilder(String keyword, int limit, int offset) {
    return new CountriesHandler(keyword, limit, offset);
  }

  public static DBHandler build21CenSkillsHandlerBuilder() {
    return new Cen21SkillsHandler();
  }
}
