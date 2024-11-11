package com.hypesofts.homember.application.instruction.parsing;

import com.hypesofts.homember.application.instruction.core.Instruction;
import com.hypesofts.homember.application.instruction.core.InstructionRequest;
import com.hypesofts.homember.application.instruction.core.TokenizedInstructionRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FrenchInstructionParser implements InstructionParser {

    private static final int COMMAND_POSITION = 0;
    private final InstructionTokenizer tokenizer;

    @Override
    public Instruction parse(InstructionRequest request) {
        TokenizedInstructionRequest tokenizedInstructionRequest = tokenizer.tokenize(request);
        return Instruction.of(tokenizedInstructionRequest);
    }
}
