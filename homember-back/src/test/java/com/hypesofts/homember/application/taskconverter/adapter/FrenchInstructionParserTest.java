package com.hypesofts.homember.application.taskconverter.adapter;

import com.hypesofts.homember.application.taskconverter.core.Command;
import com.hypesofts.homember.application.taskconverter.core.InstructionRequest;
import com.hypesofts.homember.application.taskconverter.core.Parameter;
import com.hypesofts.homember.application.taskconverter.core.ParameterType;
import com.hypesofts.homember.application.taskconverter.parsing.FrenchInstructionParser;
import com.hypesofts.homember.application.taskconverter.parsing.FrenchTokenSanitizer;
import com.hypesofts.homember.application.taskconverter.parsing.InstructionTokenizer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


class FrenchInstructionParserTest {

    private static final Parameter PARAMETER_ITEM_CLES = Parameter.of(ParameterType.ITEM, "clés");
    private static final Parameter PARAMETER_ITEM_BRIQUETS = Parameter.of(ParameterType.ITEM, "briquets");
    private static final Parameter PARAMETER_ITEM_CASQUE = Parameter.of(ParameterType.ITEM, "casque");
    private static final Parameter PARAMETER_CABINET_ARMOIRE = Parameter.of(ParameterType.CABINET, "armoire");
    private static final Parameter PARAMETER_CABINET_COMMODE = Parameter.of(ParameterType.CABINET, "commode");
    private static final Parameter PARAMETER_ROOM_SALON = Parameter.of(ParameterType.ROOM, "salon");

    private final InstructionTokenizer tokenizer = new InstructionTokenizer(new FrenchTokenSanitizer());
    private final FrenchInstructionParser parser = new FrenchInstructionParser();

    private static Stream<Arguments> testParameters() {
        return Stream.of(
                Arguments.of("range les clés dans l'armoire du salon", Command.PUT, List.of(PARAMETER_ITEM_CLES, PARAMETER_CABINET_ARMOIRE, PARAMETER_ROOM_SALON)),
                Arguments.of("enlève les briquets de la commode du salon", Command.REMOVE, List.of(PARAMETER_ITEM_BRIQUETS, PARAMETER_CABINET_COMMODE, PARAMETER_ROOM_SALON)),
                Arguments.of("trouve le casque", Command.LOCATE, List.of(PARAMETER_ITEM_CASQUE)),
                Arguments.of("autre", Command.UNDEFINED, Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("testParameters")
    void should_parse_instruction(String input, Command expected, List<Parameter> parameters) {
        InstructionRequest instructionRequest = new InstructionRequest(input);

        var instruction = parser.parse(instructionRequest, tokenizer);

        Assertions.assertThat(instruction).isNotNull();
        Assertions.assertThat(instruction.command()).isEqualTo(expected);

        Assertions.assertThat(instruction.parameters()).isNotNull();
        Assertions.assertThat(instruction.parameters()).containsExactlyInAnyOrderElementsOf(parameters);
    }

    @Test
    void should_fail_parsing_instruction_with_invalid_number_of_parameters() {
        InstructionRequest instructionRequest = new InstructionRequest("range les clés dans l'armoire");

        Assertions.assertThatThrownBy(() -> parser.parse(instructionRequest, tokenizer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid number of parameters for command PUT");
    }

}
