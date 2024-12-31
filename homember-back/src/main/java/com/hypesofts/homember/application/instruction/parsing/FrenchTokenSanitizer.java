package com.hypesofts.homember.application.instruction.parsing;

import java.util.Set;

public class FrenchTokenSanitizer implements TokenSanitizer {

    private static final Set<String> TOKENS_TO_SANITIZE = Set.of("le", "la", "les", "l",
            "de", "du", "des", "un", "une", "dans");

    @Override
    public Set<String> tokensToSanitize() {
        return TOKENS_TO_SANITIZE;
    }
}
