package com.hypesofts.homember.application.taskconverter.core;

public record Parameter(ParameterType type, String value) {
    public static Parameter of(ParameterType type, String value) {
        return new Parameter(type, value);
    }
}