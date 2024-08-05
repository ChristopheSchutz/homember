package com.hypesofts.homember.application.taskconverter.adapter;

import com.hypesofts.homember.application.taskconverter.core.Command;
import com.hypesofts.homember.application.taskconverter.core.InstructionRequest;
import com.hypesofts.homember.application.taskconverter.tokenizer.FrenchInstructionParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class FrenchInstructionParserTest {

    @ParameterizedTest
    @CsvSource({
            "range les clés dans l'armoire du salon, PUT",
            "enlève les clés de l'armoire du salon, REMOVE",
            "où sont les clés, LOCATE",
            "autre, UNDEFINED"
    })
    public void testConvertCommand(String input, Command expected) {
        InstructionRequest instructionRequest = new InstructionRequest(input);
        FrenchInstructionParser frenchInstructionParser = new FrenchInstructionParser();

        var instruction = frenchInstructionParser.parse(instructionRequest);

        Assertions.assertThat(instruction).isNotNull();
        Assertions.assertThat(instruction.command()).isEqualTo(expected);
    }
}
