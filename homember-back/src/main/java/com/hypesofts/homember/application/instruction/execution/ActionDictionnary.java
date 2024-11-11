package com.hypesofts.homember.application.instruction.execution;

import com.hypesofts.homember.application.instruction.core.Command;
import com.hypesofts.homember.application.place.core.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ActionDictionnary {

    @Autowired
    private PlaceRepository placeRepository;

    public List<Action> getActions() {
        return List.of(new Action(Command.STORE_ITEM, (place) -> placeRepository.create(null)));
    }
}
