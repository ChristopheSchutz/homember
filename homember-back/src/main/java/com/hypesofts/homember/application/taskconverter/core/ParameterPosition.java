package com.hypesofts.homember.application.taskconverter.core;

public record ParameterPosition(ParameterType type, Integer position) {
    public static ParameterPosition of(ParameterType type, Integer position) {
        return new ParameterPosition(type, position);
    }
}