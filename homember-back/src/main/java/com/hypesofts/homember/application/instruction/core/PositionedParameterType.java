package com.hypesofts.homember.application.instruction.core;

public record PositionedParameterType(ParameterType type, Integer position) {
    public static PositionedParameterType of(ParameterType type, Integer position) {
        return new PositionedParameterType(type, position);
    }
}