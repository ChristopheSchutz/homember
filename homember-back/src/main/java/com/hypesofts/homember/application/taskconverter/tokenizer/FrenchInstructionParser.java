package com.hypesofts.homember.application.taskconverter.tokenizer;

import com.hypesofts.homember.application.taskconverter.core.Command;
import com.hypesofts.homember.application.taskconverter.core.Instruction;
import com.hypesofts.homember.application.taskconverter.core.InstructionRequest;

import java.util.Arrays;
import java.util.List;

public class FrenchInstructionParser implements InstructionParser {

    private static final int COMMAND_POSITION = 0;
    private List<String> tokenizedInput;
    private Command command;

    @Override
    public Instruction parse(InstructionRequest request) {
        this.tokenizedInput = tokenizeInput(request);
        return new Instruction(getCommand());
    }

    private List<String> tokenizeInput(InstructionRequest instructionRequest) {
        return Arrays.asList(instructionRequest.input()
                .toLowerCase()
                .strip()
                .replaceAll("\\s+", " ")
                .split(" "));
    }

    public Command getCommand() {
        if (command == null) {
            command = Command.fromString(tokenizedInput.get(COMMAND_POSITION));
        }
        return command;
    }
}
