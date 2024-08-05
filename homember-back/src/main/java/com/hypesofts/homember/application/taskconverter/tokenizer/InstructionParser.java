package com.hypesofts.homember.application.taskconverter.tokenizer;

import com.hypesofts.homember.application.taskconverter.core.Instruction;
import com.hypesofts.homember.application.taskconverter.core.InstructionRequest;

public interface InstructionParser {

    Instruction parse(InstructionRequest request);
}
