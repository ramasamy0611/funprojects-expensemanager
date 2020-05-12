package com.ram.projects.expensemanager.rest.process.rest;

import com.ram.projects.expensemanager.exception.ExpMgrException;
import com.ram.projects.expensemanager.exception.UserNameExistsException;
import com.ram.projects.expensemanager.exception.UserNotFoundException;
import com.ram.projects.expensemanager.exception.UserSignInNotFoundException;
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

import static com.ram.projects.expensemanager.common.DateTimeUtils.currentTimeInstant;

@Service
public class RestProcessor<T, R, O, Y> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestProcessor.class);
    private static final String LOG_HANDLE = "[RestProcessor :] ";

    public CompletableFuture<ResponseEntity<T>> process(
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
                LOG_HANDLE + "api invoked - START :{} timestamp :{}", apiName, currentTimeInstant());
        return CompletableFuture.completedFuture(inputConverter.convert(inputEntity))
                .thenCompose(handler::apply)
                .thenApply(outputConverter::convert)
                .thenApply(this::prepareResponse)
                .thenApply(
                        respData -> {
                            LOGGER.info(
                                    LOG_HANDLE + "api invoked - END :{} timestamp :{}",
                                    apiName,
                                    currentTimeInstant());
                            return respData;
                        })
                .exceptionally(throwable -> this.prepareResponse(apiName, throwable));
    }

    public CompletableFuture<ResponseEntity<T>> process(
            String apiName, Converter<Y, T> outputConverter, Function<O, CompletableFuture<Y>> handler) {
        Objects.requireNonNull(apiName.strip(), "Api Name cannot be blank");
        Objects.requireNonNull(outputConverter, "OutputConverter cannot null");
        Objects.requireNonNull(handler, "Handler cannot null");

        LOGGER.info(
                LOG_HANDLE + "api invoked - START :{} timestamp :{}", apiName, currentTimeInstant());
        return handler
                .apply(null)
                .thenApply(outputConverter::convert)
                .thenApply(this::prepareResponse)
                .toCompletableFuture()
                .thenApply(
                        respData -> {
                            LOGGER.info(
                                    LOG_HANDLE + "api invoked - END :{} timestamp :{}",
                                    apiName,
                                    currentTimeInstant());
                            return respData;
                        })
                .exceptionally(throwable -> this.prepareResponse(apiName, throwable));
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

        LOGGER.info(
                LOG_HANDLE + "api invoked - START :{} timestamp :{}", apiName, currentTimeInstant());
        return CompletableFuture.completedFuture(inputConverter.convert(inputEntity.getBody()))
                .thenCompose(handler)
                .thenApply(outputConverter::convert)
                .thenApply(this::prepareResponse)
                .thenApply(
                        respData -> {
                            LOGGER.info(
                                    LOG_HANDLE + "api invoked - END :{} timestamp :{}",
                                    apiName,
                                    currentTimeInstant());
                            return respData;
                        })
                .exceptionally(throwable -> this.prepareResponse(apiName, throwable));
    }

    private ResponseEntity<T> prepareResponse(String apiName, Throwable throwable) {
        LOGGER.warn(LOG_HANDLE + " something went wrong :", throwable);
        String message;
        ResponseEntity<String> responseEntity;
        Throwable throwableCause = throwable.getCause();
        if (throwableCause instanceof UserNotFoundException) {
            UserNotFoundException userNotFoundException = (UserNotFoundException) throwableCause;
            message = userNotFoundException.getMessage();
            responseEntity = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        } else if (throwableCause instanceof UserNameExistsException) {
            UserNameExistsException userNameExistsException = (UserNameExistsException) throwableCause;
            message = userNameExistsException.getMessage();
            responseEntity = ResponseEntity.ok(message);
        } else if (throwableCause instanceof UserSignInNotFoundException) {
            UserSignInNotFoundException userSignInNotFoundException = (UserSignInNotFoundException) throwableCause;
            message = userSignInNotFoundException.getMessage();
            responseEntity = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        } else {
            message =
                    throwableCause instanceof ExpMgrException
                            ? ((ExpMgrException) throwableCause).getExpMgrExceptionMessage()
                            : throwable.getMessage();
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
        LOGGER.info(
                LOG_HANDLE + "api invoked - END :{} timestamp :{}",
                apiName,
                currentTimeInstant());
        return (ResponseEntity<T>) responseEntity;
    }

    private ResponseEntity<T> prepareResponse(T responseData) {
        return ResponseEntity.ok(responseData);
    }
}
