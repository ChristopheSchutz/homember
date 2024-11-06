package com.hypesofts.homember.application.instruction.adapter;

import com.hypesofts.homember.application.instruction.core.Command;
import com.hypesofts.homember.application.instruction.core.InstructionRequest;
import com.hypesofts.homember.application.instruction.core.ParameterType;
import com.hypesofts.homember.application.instruction.core.TokenizedInstruction;
import com.hypesofts.homember.application.instruction.parsing.FrenchInstructionParser;
import com.hypesofts.homember.application.instruction.parsing.InstructionTokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FrenchInstructionParserTestG {

    @Mock
    private InstructionTokenizer tokenizer;

    private FrenchInstructionParser parser;

    @BeforeEach
    void setUp() {
        parser = new FrenchInstructionParser();
    }

    // Step 1: First test - simple locate command with one parameter
    @Test
    void should_parse_locate_command_with_item() {
        // Given
        var request = new InstructionRequest("trouve clés");
        var tokenizedInstruction = new TokenizedInstruction(List.of("trouve", "clés"));
        when(tokenizer.tokenize(request)).thenReturn(tokenizedInstruction);

        // When
        var instruction = parser.parse(request, tokenizer);

        // Then
        assertThat(instruction.command()).isEqualTo(Command.LOCATE);
        assertThat(instruction.parameters()).hasSize(1);
        assertThat(instruction.parameters().get(0).type()).isEqualTo(ParameterType.ITEM);
        assertThat(instruction.parameters().get(0).value()).isEqualTo("clés");
    }

    // Step 2: Test undefined command
    @Test
    void should_return_undefined_command_when_command_is_invalid() {
        // Given
        var request = new InstructionRequest("invalid commande");
        var tokenizedInstruction = new TokenizedInstruction(List.of("invalid", "commande"));
        when(tokenizer.tokenize(request)).thenReturn(tokenizedInstruction);

        // When
        var instruction = parser.parse(request, tokenizer);

        // Then
        assertThat(instruction.command()).isEqualTo(Command.UNDEFINED);
    }

    // Step 3: Test PUT command with all parameters
    @Test
    void should_parse_put_command_with_all_parameters() {
        // Given
        var request = new InstructionRequest("range livre armoire chambre");
        var tokenizedInstruction = new TokenizedInstruction(List.of("range", "livre", "armoire", "chambre"));
        when(tokenizer.tokenize(request)).thenReturn(tokenizedInstruction);

        // When
        var instruction = parser.parse(request, tokenizer);

        // Then
        assertThat(instruction.command()).isEqualTo(Command.PUT);
        assertThat(instruction.parameters()).hasSize(3);
        assertThat(instruction.parameters().get(0).type()).isEqualTo(ParameterType.ITEM);
        assertThat(instruction.parameters().get(0).value()).isEqualTo("livre");
        assertThat(instruction.parameters().get(1).type()).isEqualTo(ParameterType.CABINET);
        assertThat(instruction.parameters().get(1).value()).isEqualTo("armoire");
        assertThat(instruction.parameters().get(2).type()).isEqualTo(ParameterType.ROOM);
        assertThat(instruction.parameters().get(2).value()).isEqualTo("chambre");
    }

    // Step 4: Test PUT command with missing parameters
    @Test
    void should_throw_exception_when_put_command_has_missing_parameters() {
        // Given
        var request = new InstructionRequest("range livre");
        var tokenizedInstruction = new TokenizedInstruction(List.of("range", "livre"));
        when(tokenizer.tokenize(request)).thenReturn(tokenizedInstruction);

        // When/Then
        assertThatThrownBy(() -> parser.parse(request, tokenizer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid number of parameters");
    }

    // Step 5: Test REMOVE command
    @Test
    void should_parse_remove_command_with_all_parameters() {
        // Given
        var request = new InstructionRequest("enlève livre armoire chambre");
        var tokenizedInstruction = new TokenizedInstruction(List.of("enlève", "livre", "armoire", "chambre"));
        when(tokenizer.tokenize(request)).thenReturn(tokenizedInstruction);

        // When
        var instruction = parser.parse(request, tokenizer);

        // Then
        assertThat(instruction.command()).isEqualTo(Command.REMOVE);
        assertThat(instruction.parameters()).hasSize(3);
        assertThat(instruction.parameters().get(0).type()).isEqualTo(ParameterType.ITEM);
        assertThat(instruction.parameters().get(0).value()).isEqualTo("livre");
        assertThat(instruction.parameters().get(1).type()).isEqualTo(ParameterType.CABINET);
        assertThat(instruction.parameters().get(1).value()).isEqualTo("armoire");
        assertThat(instruction.parameters().get(2).type()).isEqualTo(ParameterType.ROOM);
        assertThat(instruction.parameters().get(2).value()).isEqualTo("chambre");
    }
}