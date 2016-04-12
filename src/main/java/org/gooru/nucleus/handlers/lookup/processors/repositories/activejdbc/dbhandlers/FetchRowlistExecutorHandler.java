package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityMetadataReference;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.formatter.JsonFormatterBuilder;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.javalite.activejdbc.LazyList;

import java.util.List;

/**
 * Created by ashish on 5/2/16.
 */
public class FetchRowlistExecutorHandler implements DBHandler {
  private final String sql;
  private final List<String> fieldsInJson;
  private final String name;

  public FetchRowlistExecutorHandler(String name, String sql, List<String> fieldsInJson) {
    this.name = name;
    this.sql = sql;
    this.fieldsInJson = fieldsInJson;
  }

  @Override
  public ExecutionResult<MessageResponse> executeRequest() {
    LazyList<AJEntityMetadataReference> result = AJEntityMetadataReference.findBySQL(sql, name);
    JsonObject returnValue = new JsonObject().put(name, new JsonArray(
      JsonFormatterBuilder.buildSimpleJsonFormatter(false, fieldsInJson).toJson(result)));
    return new ExecutionResult<>(MessageResponseFactory.createOkayResponse(returnValue), ExecutionResult.ExecutionStatus.SUCCESSFUL);
  }

  @Override
  public boolean handlerReadOnly() {
    return true;
  }
}
