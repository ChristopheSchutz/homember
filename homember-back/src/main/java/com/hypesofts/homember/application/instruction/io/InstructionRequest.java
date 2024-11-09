package com.hypesofts.homember.application.instruction.io;

public record InstructionRequest(String input) {
    public static InstructionRequest of(String input) {
        return new InstructionRequest(input);
    }
}