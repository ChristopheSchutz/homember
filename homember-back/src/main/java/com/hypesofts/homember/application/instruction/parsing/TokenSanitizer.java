package com.hypesofts.homember.application.instruction.parsing;

import com.hypesofts.homember.application.instruction.core.TokenizedInstructionRequest;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface TokenSanitizer {

    Set<String> tokensToSanitize();

    default TokenizedInstructionRequest sanitize(TokenizedInstructionRequest request) {
        return TokenizedInstructionRequest.of(request.tokens().stream()
                .filter(token -> !tokensToSanitize().contains(token))
                .toList());
    }
}
