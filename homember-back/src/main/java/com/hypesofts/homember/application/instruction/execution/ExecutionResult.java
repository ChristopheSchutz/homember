package com.hypesofts.homember.application.instruction.execution;

import lombok.Value;

@Value
public class ExecutionResult {

    Status status;

    public static ExecutionResult success() {
        return new ExecutionResult(Status.SUCCESS);
    }

    public static ExecutionResult failure() {
        return new ExecutionResult(Status.FAILURE);
    }

    enum Status {
        SUCCESS,
        FAILURE
    }

}
