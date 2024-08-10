package com.hypesofts.homember.application.taskconverter.tokenizer;

import com.hypesofts.homember.application.taskconverter.core.Command;
import com.hypesofts.homember.application.taskconverter.core.Instruction;
import com.hypesofts.homember.application.taskconverter.core.InstructionRequest;
import com.hypesofts.homember.application.taskconverter.core.Parameter;
import com.hypesofts.homember.application.taskconverter.core.ParameterPosition;

import java.util.Arrays;
import java.util.List;

public class FrenchInstructionParser implements InstructionParser {

    private static final int COMMAND_POSITION = 0;

    @Override
    public Instruction parse(InstructionRequest request) {
        var tokenizedInput = tokenizeInput(request);
        var command = getCommand(tokenizedInput);
        return new Instruction(command, getParameters(tokenizedInput, command));
    }

    private List<String> tokenizeInput(InstructionRequest instructionRequest) {
        return Arrays.asList(instructionRequest.input()
                .toLowerCase()
                .strip()
                .replaceAll("\\s+", " ")
                .split(" "));
    }

    public Command getCommand(List<String> tokenizedInput) {
        return Command.fromString(tokenizedInput.get(COMMAND_POSITION));
    }

    public List<Parameter> getParameters(List<String> tokenizedInput, Command command) {
        return command.getParameterPositions().stream()
                .map(parameterPosition -> getParameter(tokenizedInput, parameterPosition))
                .toList();
    }

    private Parameter getParameter(List<String> tokenizedInput, ParameterPosition parameterPosition) {
        return Parameter.of(parameterPosition.type(), tokenizedInput.get(parameterPosition.position()));
    }
}
