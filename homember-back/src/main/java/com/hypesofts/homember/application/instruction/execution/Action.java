package com.hypesofts.homember.application.instruction.execution;

import com.hypesofts.homember.application.instruction.core.Command;

import java.util.function.Consumer;

public record Action(Command command, Consumer<String> targetExecution) {
    public static Action of(Command command, Consumer<String> targetExecution) {
        return new Action(command, targetExecution);
    }

    public void execute(String input) {
        targetExecution.accept(input);
    }
}
