package com.hypesofts.homember.application.taskconverter.parsing;

import com.hypesofts.homember.application.taskconverter.core.Command;
import com.hypesofts.homember.application.taskconverter.core.Instruction;
import com.hypesofts.homember.application.taskconverter.core.InstructionRequest;
import com.hypesofts.homember.application.taskconverter.core.Parameter;
import com.hypesofts.homember.application.taskconverter.core.ParameterPosition;
import com.hypesofts.homember.application.taskconverter.core.TokenizedInstruction;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FrenchInstructionParser implements InstructionParser {

    private static final int COMMAND_POSITION = 0;

    @Override
    public Instruction parse(InstructionRequest request, InstructionTokenizer tokenizer) {
        var tokenizedInput = tokenizer.tokenize(request);
        var command = buildCommand(tokenizedInput);
        verifyParameters(tokenizedInput, command);
        return Instruction.of(command, buildParameter(tokenizedInput, command));
    }

    private void verifyParameters(TokenizedInstruction tokenizedInstruction, Command command) {
        if (command.getParameterPositions().size() >= tokenizedInstruction.tokens().size()) {
            throw new IllegalArgumentException("Invalid number of parameters for command " + command);
        }
    }

    private Command buildCommand(TokenizedInstruction tokenizedInstruction) {
        return Command.fromString(tokenizedInstruction.get(COMMAND_POSITION));
    }

    private List<Parameter> buildParameter(TokenizedInstruction tokenizedInstruction, Command command) {
        return command.getParameterPositions().stream()
                .map(parameterPosition -> getParameterAtPosition(tokenizedInstruction, parameterPosition))
                .toList();
    }

    private Parameter getParameterAtPosition(TokenizedInstruction tokenizedInstruction, ParameterPosition parameterPosition) {

        return Parameter.of(parameterPosition.type(), tokenizedInstruction.get(parameterPosition.position()));
    }
}
