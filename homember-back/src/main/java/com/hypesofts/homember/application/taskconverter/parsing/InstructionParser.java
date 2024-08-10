package com.hypesofts.homember.application.taskconverter.parsing;

import com.hypesofts.homember.application.taskconverter.core.Instruction;
import com.hypesofts.homember.application.taskconverter.core.InstructionRequest;

public interface InstructionParser {

    Instruction parse(InstructionRequest request, InstructionTokenizer tokenizer);
}
