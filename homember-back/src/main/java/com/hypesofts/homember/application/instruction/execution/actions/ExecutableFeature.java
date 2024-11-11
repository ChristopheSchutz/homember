package com.hypesofts.homember.application.instruction.execution.actions;

import com.hypesofts.homember.application.instruction.core.Command;
import com.hypesofts.homember.application.instruction.core.Instruction;
import com.hypesofts.homember.application.instruction.execution.ExecutionResult;

public interface ExecutableFeature {

    Command getCommand();

    ExecutionResult execute(Instruction instruction);
}
