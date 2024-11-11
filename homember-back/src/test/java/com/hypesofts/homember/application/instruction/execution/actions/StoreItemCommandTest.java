package com.hypesofts.homember.application.instruction.execution.actions;

import com.hypesofts.homember.application.instruction.core.Command;
import com.hypesofts.homember.application.instruction.core.Instruction;
import com.hypesofts.homember.application.instruction.execution.ExecutionResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class StoreItemCommandTest {

    @Test
    void testExecution() {
        StoreItemCommand storeItemCommand = new StoreItemCommand();
        Assertions.assertThat(storeItemCommand.getCommand()).isEqualTo(Command.STORE_ITEM);
        Instruction instruction = mock(Instruction.class);
        ExecutionResult result = storeItemCommand.execute(instruction);
    }
}
