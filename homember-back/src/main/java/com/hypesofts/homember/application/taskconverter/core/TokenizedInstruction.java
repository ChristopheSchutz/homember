package com.hypesofts.homember.application.taskconverter.core;

import java.util.List;

public record TokenizedInstruction(List<String> tokens) {
    public static TokenizedInstruction of(List<String> tokens) {
        return new TokenizedInstruction(tokens);
    }

    public String get(int index) {
        return tokens.get(index);
    }
}
