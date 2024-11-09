package com.hypesofts.homember.application.instruction.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum Command {
    PUT("range", List.of("dans"), List.of(ParameterType.ITEM, ParameterType.PLACE));

    private final String order;
    private final List<String> delimiters;
    private final List<ParameterType> parameterTypes;

    public static Command fromOrder(String text) {
        return Arrays.stream(Command.values())
                .filter(command -> command.order.equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Command not found"));
    }
}