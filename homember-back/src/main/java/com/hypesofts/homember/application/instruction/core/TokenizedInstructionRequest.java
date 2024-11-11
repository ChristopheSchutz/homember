package com.hypesofts.homember.application.instruction.core;

import java.util.List;

public record TokenizedInstructionRequest(List<String> tokens) {

    public static TokenizedInstructionRequest of(List<String> tokens) {
        return new TokenizedInstructionRequest(tokens);
    }

    public Integer getDelimiterPosition(String delimiter) {
        return tokens.indexOf(delimiter);
    }

}
