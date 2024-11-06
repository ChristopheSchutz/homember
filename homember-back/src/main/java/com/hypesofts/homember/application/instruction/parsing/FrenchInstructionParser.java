package com.hypesofts.homember.application.instruction.parsing;

import com.hypesofts.homember.application.instruction.core.Command;
import com.hypesofts.homember.application.instruction.core.Instruction;
import com.hypesofts.homember.application.instruction.core.InstructionRequest;
import com.hypesofts.homember.application.instruction.core.Parameter;
import com.hypesofts.homember.application.instruction.core.PositionedParameterType;
import com.hypesofts.homember.application.instruction.core.TokenizedInstruction;
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
        if (command.getPositionedParameterTypes().size() >= tokenizedInstruction.tokens().size()) {
            throw new IllegalArgumentException("Invalid number of parameters for command " + command);
        }
    }

    private Command buildCommand(TokenizedInstruction tokenizedInstruction) {
        return Command.fromString(tokenizedInstruction.get(COMMAND_POSITION));
    }

    private List<Parameter> buildParameter(TokenizedInstruction tokenizedInstruction, Command command) {
        return command.getPositionedParameterTypes().stream()
                .map(positionedParameterType -> getParameterAtPosition(tokenizedInstruction, positionedParameterType))
                .toList();
    }

    private Parameter getParameterAtPosition(TokenizedInstruction tokenizedInstruction, PositionedParameterType positionedParameterType) {

        return Parameter.of(positionedParameterType.type(), tokenizedInstruction.get(positionedParameterType.position()));
    }
}
