package com.hypesofts.homember.application.taskconverter.core;

import java.util.List;

public record Instruction(Command command, List<Parameter> parameters) {
    public static Instruction of(Command command, List<Parameter> parameters) {
        return new Instruction(command, parameters);
    }
}
