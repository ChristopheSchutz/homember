package com.hypesofts.homember.application.instruction.core;

public record InstructionRequest(String input) {
    public static InstructionRequest of(String input) {
        return new InstructionRequest(input);
    }
}