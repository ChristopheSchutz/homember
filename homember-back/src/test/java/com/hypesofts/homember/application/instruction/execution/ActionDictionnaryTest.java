package com.hypesofts.homember.application.instruction.execution;

import com.hypesofts.homember.application.instruction.core.Command;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ActionDictionnaryTest {

    @Test
    void shouldDictionnaryHaveAllCommands() {
        ActionDictionnary dictionnary = new ActionDictionnary();
        Assertions.assertThat(dictionnary.getActions()).isNotNull();

        // Verifiy actions are properly built
        for (Action action : dictionnary.getActions()) {
            Assertions.assertThat(action).isNotNull();
            Assertions.assertThat(action.command()).isNotNull();
            Assertions.assertThat(action.targetExecution()).isNotNull();
        }

        // Verify all commmands have an action
        for (Command command : Command.values()) {
            Assertions.assertThat(dictionnary.getActions().stream().anyMatch(action -> action.command().equals(command))).isTrue();
        }
    }

    @Test
    void shouldActionHaveExecution() {
    }
}
