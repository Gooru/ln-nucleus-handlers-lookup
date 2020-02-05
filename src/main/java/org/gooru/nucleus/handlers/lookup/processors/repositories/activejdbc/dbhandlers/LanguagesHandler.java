package org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.dbhandlers;

import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.entities.AJEntityGooruLanguage;
import org.gooru.nucleus.handlers.lookup.processors.repositories.activejdbc.formatter.JsonFormatterBuilder;
import org.gooru.nucleus.handlers.lookup.processors.responses.ExecutionResult;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.javalite.activejdbc.LazyList;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

class LanguagesHandler implements DBHandler {

  @Override
  public ExecutionResult<MessageResponse> executeRequest() {
    LazyList<AJEntityGooruLanguage> result = AJEntityGooruLanguage
        .where(AJEntityGooruLanguage.FETCH_LANGUAGES).orderBy(AJEntityGooruLanguage.SEQUENCE_ID);
    JsonObject response =
        new JsonObject().put(AJEntityGooruLanguage.LANGUAGES, new JsonArray(JsonFormatterBuilder
            .buildSimpleJsonFormatter(false, AJEntityGooruLanguage.FETCH_FIELDS).toJson(result)));
    return new ExecutionResult<>(MessageResponseFactory.createOkayResponse(response),
        ExecutionResult.ExecutionStatus.SUCCESSFUL);
  }

  @Override
  public boolean handlerReadOnly() {
    return true;
  }

}
