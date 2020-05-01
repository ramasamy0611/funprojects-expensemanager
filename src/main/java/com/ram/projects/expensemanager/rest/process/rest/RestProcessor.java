package com.ram.projects.expensemanager.rest.process.rest;

import com.ram.projects.expensemanager.rest.converter.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class RestProcessor<T, R, I, O> {
  private static final Logger LOGGER = LoggerFactory.getLogger(RestProcessor.class);
  public CompletableFuture<ResponseEntity<T>> process(
          String apiName,
          T inputEntity,
          Converter<T, O> inputConverter,
          Converter<R, T> outputConverter,
          Function<O, CompletableFuture<R>> handler) {
    Objects.requireNonNull(apiName.strip(), "Api Name cannot be blank");
    Objects.requireNonNull(inputEntity, "HttpEntity cannot null");
    Objects.requireNonNull(inputConverter, "InputConverter cannot null");
    Objects.requireNonNull(inputEntity, "OutputConverter cannot null");
    Objects.requireNonNull(inputEntity, "Handler cannot null");

    LOGGER.info("[RestProcessor:] api invoked - START:{}", apiName);
    return CompletableFuture.completedFuture(inputConverter.convert(inputEntity))
            .thenCompose(handler)
            .thenApply(outputConverter::convert)
            .thenApply(this::prepareResponse)
            .thenApply(
                    respData -> {
                      LOGGER.info("[RestProcessor:] api invoked - END :{}", apiName);
                      return respData;
                    })
            .exceptionally(this::prepareResponse);
  }
  public CompletableFuture<ResponseEntity<T>> process(
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

    LOGGER.info("[RestProcessor:] api invoked - START:{}", apiName);
    return CompletableFuture.completedFuture(inputConverter.convert(inputEntity.getBody()))
        .thenCompose(handler)
        .thenApply(outputConverter::convert)
        .thenApply(this::prepareResponse)
        .thenApply(
            respData -> {
              LOGGER.info("[RestProcessor:] api invoked - END :{}", apiName);
              return respData;
            })
        .exceptionally(this::prepareResponse);
  }

  private ResponseEntity<T> prepareResponse(Throwable throwable) {
    LOGGER.info("[RestProcessor:] something went wrong :", throwable);
    return (ResponseEntity<T>) ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(throwable);
  }

  private ResponseEntity<T> prepareResponse(T responseData) {
    return ResponseEntity.ok(responseData);
  }
}
