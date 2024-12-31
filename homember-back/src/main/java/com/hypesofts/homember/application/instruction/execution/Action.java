package com.hypesofts.homember.application.instruction.execution;

import com.hypesofts.homember.application.instruction.core.Commands;

import java.util.function.Consumer;

public record Action(Commands command, Consumer<String> targetExecution) {
    public static Action of(Commands command, Consumer<String> targetExecution) {
        return new Action(command, targetExecution);
    }

    public void execute(String input) {
        targetExecution.accept(input);
    }
}
