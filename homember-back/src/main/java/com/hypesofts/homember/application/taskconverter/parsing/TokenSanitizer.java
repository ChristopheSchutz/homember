package com.hypesofts.homember.application.taskconverter.parsing;

import com.hypesofts.homember.application.taskconverter.core.TokenizedInstruction;

import java.util.Set;

public interface TokenSanitizer {

    Set<String> tokensToSanitize();

    default TokenizedInstruction sanitize(TokenizedInstruction request) {
        return TokenizedInstruction.of(request.tokens().stream()
                .filter(token -> !tokensToSanitize().contains(token))
                .toList());
    }
}
