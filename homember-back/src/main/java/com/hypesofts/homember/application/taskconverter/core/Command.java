package com.hypesofts.homember.application.taskconverter.core;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public enum Command {
    PUT("range", List.of(ParameterPosition.of(ParameterType.ITEM, 1), ParameterPosition.of(ParameterType.CABINET, 2), ParameterPosition.of(ParameterType.ROOM, 3))),
    REMOVE("enlève", List.of(ParameterPosition.of(ParameterType.ITEM, 1), ParameterPosition.of(ParameterType.CABINET, 2), ParameterPosition.of(ParameterType.ROOM, 3))),
    LOCATE("trouve", List.of(ParameterPosition.of(ParameterType.ITEM, 1))),
    UNDEFINED("undefined", Collections.emptyList());

    private final String word;
    private final List<ParameterPosition> parameterPositions;

    Command(String word, List<ParameterPosition> parameterPositions) {
        this.word = word;
        this.parameterPositions = parameterPositions;
    }

    public static Command fromString(String text) {
        for (Command command : Command.values()) {
            if (command.word.equalsIgnoreCase(text)) {
                return command;
            }
        }
        return UNDEFINED;
    }
}