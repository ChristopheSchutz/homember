package com.hypesofts.homember.application.instruction.parsing;

import com.hypesofts.homember.application.instruction.core.Instruction;
import com.hypesofts.homember.application.instruction.core.InstructionRequest;

public interface InstructionParser {

    Instruction parse(InstructionRequest request, InstructionTokenizer tokenizer);
}
