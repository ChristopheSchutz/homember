package com.hypesofts.homember.application.instruction.core;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Instruction {
    private static final String JOIN_CHARACTER = " ";

    private TokenizedInstructionRequest tokenizedInstructionRequest;
    @Getter
    private Command command;
    @Getter
    private List<Parameter> parameters;

    private Instruction(TokenizedInstructionRequest tokenizedInstructionRequest) {
        this.tokenizedInstructionRequest = tokenizedInstructionRequest;
        this.command = buildCommand(tokenizedInstructionRequest);
        this.parameters = buildParameters(tokenizedInstructionRequest);
    }

    public static Instruction of(TokenizedInstructionRequest tokenizedInstructionRequest) {
        return new Instruction(tokenizedInstructionRequest);
    }

    private Command buildCommand(TokenizedInstructionRequest tokenizedInstructionRequest) {
        return Command.fromOrder(tokenizedInstructionRequest.tokens().get(0));
    }

    public List<Parameter> buildParameters(TokenizedInstructionRequest tokenizedInstructionRequest) {
        List<String> delimiters = command.getDelimiters();
        List<ParameterType> parameterTypes = command.getParameterTypes();

        // Validate delimiters
        delimiters.stream()
                .filter(delimiter -> tokenizedInstructionRequest.getDelimiterPosition(delimiter) == -1)
                .findFirst()
                .ifPresent(delimiter -> {
                    throw new IllegalArgumentException("Missing delimiter '" + delimiter + "'");
                });

        List<Parameter> parameters = new ArrayList<>();

        // Build parameters
        for (int i = 0; i <= delimiters.size(); i++) {
            int startPos = (i == 0) ? 1 : tokenizedInstructionRequest.getDelimiterPosition(delimiters.get(i - 1)) + 1;
            int endPos = (i < delimiters.size()) ? tokenizedInstructionRequest.getDelimiterPosition(delimiters.get(i)) : tokenizedInstructionRequest.tokens().size();

            var parameterTokens = tokenizedInstructionRequest.tokens().subList(startPos, endPos);
            if (parameterTokens.isEmpty()) {
                throw new IllegalArgumentException("Missing required parameter of type " + parameterTypes.get(i));
            }

            var parameterValue = String.join(JOIN_CHARACTER, parameterTokens);
            parameters.add(Parameter.of(parameterTypes.get(i), parameterValue));
        }
        return parameters;
    }
}
