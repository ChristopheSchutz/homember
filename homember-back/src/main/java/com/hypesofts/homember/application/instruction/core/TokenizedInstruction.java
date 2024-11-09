package com.hypesofts.homember.application.instruction.core;

import java.util.ArrayList;
import java.util.List;

public record TokenizedInstruction(List<String> tokens) {

    private static final String JOIN_CHARACTER = " ";

    public static TokenizedInstruction of(List<String> tokens) {
        return new TokenizedInstruction(tokens);
    }


    public Integer getDelimiterPosition(String delimiter) {
        return tokens.indexOf(delimiter);
    }

    public Command getCommand(Integer position) {
        return Command.fromOrder(tokens.get(position));
    }

    public List<Parameter> getParameters() {
        var command = getCommand(0);
        List<String> delimiters = command.getDelimiters();
        List<ParameterType> parameterTypes = command.getParameterTypes();

        // Validate delimiters
        for (String delimiter : delimiters) {
            if (getDelimiterPosition(delimiter) == -1) {
                throw new IllegalArgumentException("Missing delimiter '" + delimiter + "'");
            }
        }

        List<Parameter> parameters = new ArrayList<>();

        // Get first parameter (before first delimiter)
        var firstDelimiterPos = delimiters.isEmpty() ? tokens.size() : getDelimiterPosition(delimiters.get(0));
        var firstParameterTokens = tokens.subList(1, firstDelimiterPos);
        if (firstParameterTokens.isEmpty()) {
            throw new IllegalArgumentException("Missing required parameter of type " + parameterTypes.get(0));
        }

        var firstParameterValue = String.join(JOIN_CHARACTER, firstParameterTokens);
        parameters.add(Parameter.of(parameterTypes.get(0), firstParameterValue));

        // Get remaining parameters (after delimiters)
        for (int i = 0; i < delimiters.size(); i++) {
            var startPos = getDelimiterPosition(delimiters.get(i)) + 1;
            var endPos = i < delimiters.size() - 1
                    ? getDelimiterPosition(delimiters.get(i + 1))
                    : tokens.size();

            var parameterTokens = tokens.subList(startPos, endPos);
            if (parameterTokens.isEmpty()) {
                throw new IllegalArgumentException("Missing required parameter of type " + parameterTypes.get(i + 1));
            }

            var parameterValue = String.join(JOIN_CHARACTER, parameterTokens);
            parameters.add(Parameter.of(parameterTypes.get(i + 1), parameterValue));
        }

        return parameters;
    }
}
