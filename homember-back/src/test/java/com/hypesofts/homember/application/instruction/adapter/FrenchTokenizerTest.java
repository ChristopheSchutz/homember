package com.hypesofts.homember.application.instruction.adapter;

import com.hypesofts.homember.application.instruction.io.InstructionRequest;
import com.hypesofts.homember.application.instruction.parsing.FrenchTokenSanitizer;
import com.hypesofts.homember.application.instruction.parsing.InstructionTokenizer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class FrenchTokenizerTest {

    private final InstructionTokenizer instructionTokenizer = new InstructionTokenizer(new FrenchTokenSanitizer());

    @ParameterizedTest
    @MethodSource("testParameters")
    void should_tokenize_input(String input, List<String> expected) {
        var tokenizedInstruction = instructionTokenizer.tokenize(InstructionRequest.of(input));
        var tokens = tokenizedInstruction.tokens();
        Assertions.assertThat(tokens).containsExactlyElementsOf(expected);
    }

    private static Stream<Arguments> testParameters() {
        return Stream.of(
                Arguments.of("range les clés dans l'armoire du salon", List.of("range", "clés", "armoire", "salon")),
                Arguments.of("enlève les briquets de la commode du salon", List.of("enlève", "briquets", "commode", "salon")),
                Arguments.of("trouve les décorations", List.of("trouve", "décorations")),
                Arguments.of("autre", List.of("autre"))
        );
    }
}
