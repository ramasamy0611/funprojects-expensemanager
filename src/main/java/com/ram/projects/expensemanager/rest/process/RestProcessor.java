package com.ram.projects.expensemanager.rest.process;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class RestProcessor<T, R, I, O> {

    public CompletableFuture<ResponseEntity<T>> process(String apiName, I inputEntity, Converter<I, O> inputConverter, Converter<R, T> outputConverter, Function<O, CompletableFuture<R>> handler) {
        return CompletableFuture.completedFuture(inputConverter.convert(inputEntity))
                .thenCompose(entityToProcess -> handler.apply(entityToProcess))
                .thenApply(responseData -> outputConverter.convert(responseData))
                .thenApply(responseDataConverted -> prepareResponse(responseDataConverted))
                .exceptionally(throwable -> prepareResponse(throwable));
    }

    private ResponseEntity<T> prepareResponse(Throwable throwable) {
        return (ResponseEntity<T>) ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(throwable);
    }

    private ResponseEntity<T> prepareResponse(T responseData) {
        return ResponseEntity.ok(responseData);
    }
}
