package com.hypesofts.homember.application.taskconverter.parsing;

import com.hypesofts.homember.application.taskconverter.core.InstructionRequest;
import com.hypesofts.homember.application.taskconverter.core.TokenizedInstruction;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class InstructionTokenizer {

    public static final String MULTIPLE_WHITESPACES = "\\s+";
    private static final String SINGLE_WHITESPACE = " ";
    private static final String APOSTROPHE = "'";
    private TokenSanitizer sanitizer;

    private static List<String> buildTokens(InstructionRequest request) {
        return Arrays.asList(request.input()
                .toLowerCase()
                .strip()
                .replaceAll(MULTIPLE_WHITESPACES, SINGLE_WHITESPACE)
                .replace(APOSTROPHE, SINGLE_WHITESPACE)
                .split(SINGLE_WHITESPACE));
    }

    public TokenizedInstruction tokenize(InstructionRequest request) {
        return sanitizer.sanitize(TokenizedInstruction.of(buildTokens(request)));
    }
}
