package com.hypesofts.homember.application.instruction.adapter;

import com.hypesofts.homember.application.instruction.core.Command;
import com.hypesofts.homember.application.instruction.core.Instruction;
import com.hypesofts.homember.application.instruction.core.Parameter;
import com.hypesofts.homember.application.instruction.core.ParameterType;
import com.hypesofts.homember.application.instruction.core.TokenizedInstruction;
import com.hypesofts.homember.application.instruction.io.InstructionRequest;
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
class FrenchInstructionParserTest {

    @Mock
    private InstructionTokenizer tokenizer;

    private FrenchInstructionParser parser;

    @BeforeEach
    void setUp() {
        parser = new FrenchInstructionParser();
    }

    @Test
    void should_parse_simple_put_instruction() {
        // Given
        var request = new InstructionRequest("range la clé dans l'armoire");
        var tokenizedInstruction = TokenizedInstruction.of(
                List.of("range", "la", "clé", "dans", "l'armoire")
        );
        when(tokenizer.tokenize(request)).thenReturn(tokenizedInstruction);

        // When
        Instruction instruction = parser.parse(request, tokenizer);

        // Then
        assertThat(instruction.command()).isEqualTo(Command.PUT);
        assertThat(instruction.parameters()).hasSize(2);
        assertThat(instruction.parameters().get(0))
                .isEqualTo(Parameter.of(ParameterType.ITEM, "la clé"));
        assertThat(instruction.parameters().get(1))
                .isEqualTo(Parameter.of(ParameterType.PLACE, "l'armoire"));
    }

    @Test
    void should_parse_put_instruction_with_multi_word_parameters() {
        // Given
        var request = new InstructionRequest("range les déguisements dans le meuble à gauche");
        var tokenizedInstruction = TokenizedInstruction.of(
                List.of("range", "les", "déguisements", "dans", "le", "meuble", "à", "gauche")
        );
        when(tokenizer.tokenize(request)).thenReturn(tokenizedInstruction);

        // When
        Instruction instruction = parser.parse(request, tokenizer);

        // Then
        assertThat(instruction.command()).isEqualTo(Command.PUT);
        assertThat(instruction.parameters()).hasSize(2);
        assertThat(instruction.parameters().get(0))
                .isEqualTo(Parameter.of(ParameterType.ITEM, "les déguisements"));
        assertThat(instruction.parameters().get(1))
                .isEqualTo(Parameter.of(ParameterType.PLACE, "le meuble à gauche"));
    }

    @Test
    void should_parse_instruction_with_special_characters() {
        // Given
        var request = new InstructionRequest("range l'écharpe dans l'armoire-penderie");
        var tokenizedInstruction = TokenizedInstruction.of(
                List.of("range", "l'écharpe", "dans", "l'armoire-penderie")
        );
        when(tokenizer.tokenize(request)).thenReturn(tokenizedInstruction);

        // When
        Instruction instruction = parser.parse(request, tokenizer);

        // Then
        assertThat(instruction.command()).isEqualTo(Command.PUT);
        assertThat(instruction.parameters()).hasSize(2);
        assertThat(instruction.parameters().get(0))
                .isEqualTo(Parameter.of(ParameterType.ITEM, "l'écharpe"));
        assertThat(instruction.parameters().get(1))
                .isEqualTo(Parameter.of(ParameterType.PLACE, "l'armoire-penderie"));
    }

    @Test
    void should_throw_exception_when_delimiter_is_missing() {
        // Given
        var request = new InstructionRequest("range la clé l'armoire");  // missing "dans"
        var tokenizedInstruction = TokenizedInstruction.of(
                List.of("range", "la", "clé", "l'armoire")
        );
        when(tokenizer.tokenize(request)).thenReturn(tokenizedInstruction);

        // When/Then
        assertThatThrownBy(() -> parser.parse(request, tokenizer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing delimiter 'dans'");
    }

    @Test
    void should_throw_exception_when_before_parameter_is_missing() {
        // Given
        var request = new InstructionRequest("range dans l'armoire");  // missing item parameter
        var tokenizedInstruction = TokenizedInstruction.of(
                List.of("range", "dans", "l'armoire")
        );
        when(tokenizer.tokenize(request)).thenReturn(tokenizedInstruction);

        // When/Then
        assertThatThrownBy(() -> parser.parse(request, tokenizer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing required parameter of type ITEM");
    }

    @Test
    void should_throw_exception_when_after_parameter_is_missing() {
        // Given
        var request = new InstructionRequest("range la clé dans");  // missing item parameter
        var tokenizedInstruction = TokenizedInstruction.of(
                List.of("range", "la", "clé", "dans")
        );
        when(tokenizer.tokenize(request)).thenReturn(tokenizedInstruction);

        // When/Then
        assertThatThrownBy(() -> parser.parse(request, tokenizer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Missing required parameter of type PLACE");
    }
}