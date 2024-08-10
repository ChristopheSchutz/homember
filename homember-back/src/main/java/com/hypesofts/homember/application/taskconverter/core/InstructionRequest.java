package com.hypesofts.homember.application.taskconverter.core;

public record InstructionRequest(String input) {
    public static InstructionRequest of(String input) {
        return new InstructionRequest(input);
    }
}