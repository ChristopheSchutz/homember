package com.hypesofts.homember.application.instruction.parsing;

import com.hypesofts.homember.application.instruction.core.TokenizedInstruction;

import java.util.Set;

public interface TokenSanitizer {

    Set<String> tokensToSanitize();

    default TokenizedInstruction sanitize(TokenizedInstruction request) {
        return TokenizedInstruction.of(request.tokens().stream()
                .filter(token -> !tokensToSanitize().contains(token))
                .toList());
    }
}
