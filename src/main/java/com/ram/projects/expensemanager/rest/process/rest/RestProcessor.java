package com.ram.projects.expensemanager.rest.process.rest;

import com.ram.projects.expensemanager.exception.ExpMgrException;
import com.ram.projects.expensemanager.rest.RestResponse;
import com.ram.projects.expensemanager.rest.converter.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static com.ram.projects.expensemanager.common.DateTimeUtils.currentTimeInstant;

@Service
public class RestProcessor<T, R, O, Y> {
  private static final Logger LOGGER = LoggerFactory.getLogger(RestProcessor.class);
  private static final String LOG_HENDLE = "[RestProcessor :] ";

  public CompletableFuture<ResponseEntity<RestResponse<T>>> process(
      String apiName,
      T inputEntity,
      Converter<T, O> inputConverter,
      Converter<R, T> outputConverter,
      Function<O, CompletableFuture<R>> handler) {
    Objects.requireNonNull(apiName.strip(), "Api Name cannot be blank");
    Objects.requireNonNull(inputEntity, "HttpEntity cannot null");
    Objects.requireNonNull(inputConverter, "InputConverter cannot null");
    Objects.requireNonNull(outputConverter, "OutputConverter cannot null");
    Objects.requireNonNull(handler, "Handler cannot null");

    LOGGER.info(
        LOG_HENDLE + "api invoked - START :{} timestamp :{}", apiName, currentTimeInstant());
    return CompletableFuture.completedFuture(inputConverter.convert(inputEntity))
        .thenCompose(handler)
        .thenApply(outputConverter::convert)
        .thenApply(this::prepareResponse)
        .thenApply(
            respData -> {
              LOGGER.info(
                  LOG_HENDLE + "api invoked - END :{} timestamp :{}",
                  apiName,
                  currentTimeInstant());
              return respData;
            })
        .exceptionally(this::prepareResponse);
  }

  public CompletableFuture<ResponseEntity<RestResponse<T>>> process(
      String apiName, Converter<Y, T> outputConverter, Function<O, CompletableFuture<Y>> handler) {
    Objects.requireNonNull(apiName.strip(), "Api Name cannot be blank");
    Objects.requireNonNull(outputConverter, "OutputConverter cannot null");
    Objects.requireNonNull(handler, "Handler cannot null");

    LOGGER.info(
        LOG_HENDLE + "api invoked - START :{} timestamp :{}", apiName, currentTimeInstant());
    return handler
        .apply(null)
        .thenApply(outputConverter::convert)
        .thenApply(this::prepareResponse)
        .toCompletableFuture()
        .thenApply(
            respData -> {
              LOGGER.info(
                  LOG_HENDLE + "api invoked - END :{} timestamp :{}",
                  apiName,
                  currentTimeInstant());
              return respData;
            })
        .exceptionally(this::prepareResponse);
  }

  public CompletableFuture<ResponseEntity<RestResponse<T>>> process(
      String apiName,
      HttpEntity<T> inputEntity,
      Converter<T, O> inputConverter,
      Converter<R, T> outputConverter,
      Function<O, CompletableFuture<R>> handler) {
    Objects.requireNonNull(apiName.strip(), "Api Name cannot be blank");
    Objects.requireNonNull(inputEntity, "HttpEntity cannot null");
    Objects.requireNonNull(inputConverter, "InputConverter cannot null");
    Objects.requireNonNull(inputEntity, "OutputConverter cannot null");
    Objects.requireNonNull(inputEntity, "Handler cannot null");

    LOGGER.info(
        LOG_HENDLE + "api invoked - START :{} timestamp :{}", apiName, currentTimeInstant());
    return CompletableFuture.completedFuture(inputConverter.convert(inputEntity.getBody()))
        .thenCompose(handler)
        .thenApply(outputConverter::convert)
        .thenApply(this::prepareResponse)
        .thenApply(
            respData -> {
              LOGGER.info(
                  LOG_HENDLE + "api invoked - END :{} timestamp :{}",
                  apiName,
                  currentTimeInstant());
              return respData;
            })
        .exceptionally(this::prepareResponse);
  }

  private ResponseEntity<RestResponse<T>> prepareResponse(Throwable throwable) {
    LOGGER.error(LOG_HENDLE + " something went wrong :", throwable);
    String message =
        throwable.getCause() instanceof ExpMgrException
            ? ((ExpMgrException) throwable.getCause()).getExpMgrExceptionMessage()
            : throwable.getMessage();
    return ResponseEntity.ok(RestResponse.newResponse().message(message).responsePayLoad("Empty"));
  }

  private ResponseEntity<RestResponse<T>> prepareResponse(T responseData) {
    return ResponseEntity.ok(
        RestResponse.newResponse().message("Success!").responsePayLoad(responseData));
  }
}
