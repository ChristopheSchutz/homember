package com.hypesofts.homember.application.capabilities;

import com.hypesofts.homember.application.instruction.core.Commands;
import com.hypesofts.homember.application.instruction.core.Instruction;
import com.hypesofts.homember.application.instruction.core.ParameterType;
import com.hypesofts.homember.application.instruction.execution.ExecutionResult;
import com.hypesofts.homember.application.instruction.execution.actions.ExecutableCapability;
import com.hypesofts.homember.application.place.api.PlaceResource;
import com.hypesofts.homember.application.place.usecase.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreItemCapability implements ExecutableCapability {

    @Autowired
    private PlaceService placeService;

    @Override
    public Commands getCommand() {
        return Commands.STORE_ITEM;
    }

    @Override
    public ExecutionResult execute(Instruction instruction) {
        PlaceResource place = placeService.findByNameOrCreate(instruction.getFirstParameterOfType(ParameterType.PLACE).value());

        return ExecutionResult.success();
    }
}
