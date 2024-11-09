package com.hypesofts.homember.application.instruction.parsing;

import com.hypesofts.homember.application.instruction.core.Instruction;
import com.hypesofts.homember.application.instruction.core.TokenizedInstruction;
import com.hypesofts.homember.application.instruction.io.InstructionRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FrenchInstructionParser implements InstructionParser {

    private static final int COMMAND_POSITION = 0;

    @Override
    public Instruction parse(InstructionRequest request, InstructionTokenizer tokenizer) {
        TokenizedInstruction tokenizedInput = tokenizer.tokenize(request);
        var command = tokenizedInput.getCommand(COMMAND_POSITION);
        var parameters = tokenizedInput.getParameters();
        return Instruction.of(command, parameters);
    }
    // range la clé dans l'armoire
    // range les déguisements dans le meuble à gauche dans la chambre
}
