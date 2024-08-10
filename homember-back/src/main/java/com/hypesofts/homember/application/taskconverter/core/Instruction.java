package com.hypesofts.homember.application.taskconverter.core;

import java.util.List;

public record Instruction(Command command, List<Parameter> parameters) {
}
