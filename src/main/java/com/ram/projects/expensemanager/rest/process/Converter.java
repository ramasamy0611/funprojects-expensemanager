package com.ram.projects.expensemanager.rest.process;

@FunctionalInterface
public interface Converter<I, O> {
    O convert(I input);
}
