package com.hypesofts.homember.application.capabilities.core;

import com.hypesofts.homember.application.instruction.core.Commands;
import com.hypesofts.homember.application.instruction.core.Instruction;

public interface ExecutableCapability {

    Commands getCommand();

    ExecutionResult execute(Instruction instruction);

    default boolean isApplicable(Instruction instruction) {
        return instruction.getCommand().equals(getCommand());
    }
}
