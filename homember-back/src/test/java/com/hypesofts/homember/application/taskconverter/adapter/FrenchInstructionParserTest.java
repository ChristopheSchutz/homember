package com.hypesofts.homember.application.taskconverter.adapter;

import com.hypesofts.homember.application.taskconverter.core.Command;
import com.hypesofts.homember.application.taskconverter.core.InstructionRequest;
import com.hypesofts.homember.application.taskconverter.core.Parameter;
import com.hypesofts.homember.application.taskconverter.core.ParameterType;
import com.hypesofts.homember.application.taskconverter.tokenizer.FrenchInstructionParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


public class FrenchInstructionParserTest {

    private static final Parameter PARAMETER_ITEM_CLES = Parameter.of(ParameterType.ITEM, "clés");
    private static final Parameter PARAMETER_ITEM_BRIQUETS = Parameter.of(ParameterType.ITEM, "briquets");
    private static final Parameter PARAMETER_ITEM_CASQUE = Parameter.of(ParameterType.ITEM, "casque");
    private static final Parameter PARAMETER_CABINET_ARMOIRE = Parameter.of(ParameterType.CABINET, "armoire");
    private static final Parameter PARAMETER_CABINET_COMMODE = Parameter.of(ParameterType.CABINET, "commode");
    private static final Parameter PARAMETER_ROOM_SALON = Parameter.of(ParameterType.ROOM, "salon");

    private static Stream<Arguments> expectedValues() {
//        return Stream.of(
//                Arguments.of("range les clés dans l'armoire du salon", Command.PUT, List.of(PARAMETER_ITEM_CLES))
//                Arguments.of("enlève les briquets de la commode du salon", Command.REMOVE),
//                Arguments.of("où sont les décorations, LOCATE", Command.LOCATE),
//                Arguments.of("autre", Command.UNDEFINED)
//        );
        return Stream.of(
                Arguments.of("range clés armoire salon", Command.PUT, List.of(PARAMETER_ITEM_CLES, PARAMETER_CABINET_ARMOIRE, PARAMETER_ROOM_SALON)),
                Arguments.of("enlève briquets commode salon", Command.REMOVE, List.of(PARAMETER_ITEM_BRIQUETS, PARAMETER_CABINET_COMMODE, PARAMETER_ROOM_SALON)),
                Arguments.of("où casque", Command.LOCATE, List.of(PARAMETER_ITEM_CASQUE)),
                Arguments.of("autre", Command.UNDEFINED, Collections.EMPTY_LIST)
        );

    }

    @ParameterizedTest
    @MethodSource("expectedValues")
    public void testConvertCommand(String input, Command expected, List<Parameter> parameters) {
        InstructionRequest instructionRequest = new InstructionRequest(input);
        FrenchInstructionParser frenchInstructionParser = new FrenchInstructionParser();

        var instruction = frenchInstructionParser.parse(instructionRequest);

        Assertions.assertThat(instruction).isNotNull();
        Assertions.assertThat(instruction.command()).isEqualTo(expected);

        Assertions.assertThat(instruction.parameters()).isNotNull();
        Assertions.assertThat(instruction.parameters()).containsExactlyInAnyOrderElementsOf(parameters);
    }


}
