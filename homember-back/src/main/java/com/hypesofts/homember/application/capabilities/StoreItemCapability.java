package com.hypesofts.homember.application.capabilities;

import com.hypesofts.homember.application.capabilities.core.ExecutableCapability;
import com.hypesofts.homember.application.capabilities.core.ExecutionResult;
import com.hypesofts.homember.application.instruction.core.Commands;
import com.hypesofts.homember.application.instruction.core.Instruction;
import com.hypesofts.homember.application.instruction.core.Parameter;
import com.hypesofts.homember.application.instruction.core.ParameterType;
import com.hypesofts.homember.application.items.core.ItemService;
import com.hypesofts.homember.application.place.core.PlaceEntity;
import com.hypesofts.homember.application.place.core.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StoreItemCapability implements ExecutableCapability {

    private PlaceService placeService;
    private ItemService itemService;

    @Override
    public Commands getCommand() {
        return Commands.STORE_ITEM;
    }

    @Override
    public ExecutionResult execute(Instruction instruction) {
        Parameter placeParameter = instruction.getFirstParameterOfType(ParameterType.PLACE);
        Parameter itemParameter = instruction.getFirstParameterOfType(ParameterType.ITEM);

        PlaceEntity place = placeService.findByNameOrCreate(placeParameter.value());
        itemService.findByNameAndPlaceOrCreate(itemParameter.value(), place.getId());

        return ExecutionResult.success();
    }
}
