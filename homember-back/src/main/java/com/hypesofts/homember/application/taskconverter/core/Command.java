package com.hypesofts.homember.application.taskconverter.core;

public enum Command {
    PUT("range"),
    REMOVE("enlève"),
    LOCATE("où"),
    UNDEFINED("undefined");

    private String word;

    Command(String word) {
        this.word = word;
    }

    public static Command fromString(String text) {
        for (Command b : Command.values()) {
            if (b.word.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return UNDEFINED;
    }
}