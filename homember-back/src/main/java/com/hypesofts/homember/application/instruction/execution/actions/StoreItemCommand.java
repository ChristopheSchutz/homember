package com.hypesofts.homember.application.instruction.execution.actions;

import com.hypesofts.homember.application.instruction.core.Command;
import com.hypesofts.homember.application.instruction.core.Instruction;
import com.hypesofts.homember.application.instruction.execution.ExecutionResult;
import org.springframework.stereotype.Component;

@Component
public class StoreItemCommand implements ExecutableFeature {

    @Override
    public Command getCommand() {
        return Command.STORE_ITEM;
    }

    @Override
    public ExecutionResult execute(Instruction instruction) {
        return null;
    }
}
