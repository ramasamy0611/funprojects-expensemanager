package com.ram.projects.expensemanager.rest.converter;

@FunctionalInterface
public interface Converter<I, O> {
    O convert(I input);
}
