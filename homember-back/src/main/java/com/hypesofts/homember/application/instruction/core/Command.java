package com.hypesofts.homember.application.instruction.core;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public enum Command {
    PUT("range", List.of(PositionedParameterType.of(ParameterType.ITEM, 1), PositionedParameterType.of(ParameterType.CABINET, 2), PositionedParameterType.of(ParameterType.ROOM, 3))),
    REMOVE("enlève", List.of(PositionedParameterType.of(ParameterType.ITEM, 1), PositionedParameterType.of(ParameterType.CABINET, 2), PositionedParameterType.of(ParameterType.ROOM, 3))),
    LOCATE("trouve", List.of(PositionedParameterType.of(ParameterType.ITEM, 1))),
    UNDEFINED("undefined", Collections.emptyList());

    private final String word;
    private final List<PositionedParameterType> positionedParameterTypes;

    Command(String word, List<PositionedParameterType> positionedParameterTypes) {
        this.word = word;
        this.positionedParameterTypes = positionedParameterTypes;
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