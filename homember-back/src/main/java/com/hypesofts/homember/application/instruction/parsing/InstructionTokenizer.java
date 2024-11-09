package com.hypesofts.homember.application.instruction.parsing;

import com.hypesofts.homember.application.instruction.core.TokenizedInstruction;
import com.hypesofts.homember.application.instruction.io.InstructionRequest;
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
