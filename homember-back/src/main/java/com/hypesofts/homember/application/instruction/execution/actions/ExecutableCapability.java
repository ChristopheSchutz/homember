package com.hypesofts.homember.application.instruction.execution.actions;

import com.hypesofts.homember.application.instruction.core.Commands;
import com.hypesofts.homember.application.instruction.core.Instruction;
import com.hypesofts.homember.application.instruction.execution.ExecutionResult;

public interface ExecutableCapability {

    Commands getCommand();

    ExecutionResult execute(Instruction instruction);

    default boolean isApplicable(Instruction instruction) {
        return instruction.getCommand().equals(getCommand());
    }
}
