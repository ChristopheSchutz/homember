package com.hypesofts.homember.application.instruction.parsing;

import com.hypesofts.homember.application.instruction.core.InstructionRequest;
import com.hypesofts.homember.application.instruction.core.TokenizedInstructionRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Component
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

    public TokenizedInstructionRequest tokenize(InstructionRequest request) {
        return sanitizer.sanitize(TokenizedInstructionRequest.of(buildTokens(request)));
    }
}
