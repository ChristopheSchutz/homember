package com.hypesofts.homember.room;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ResponseBody
public class RoomAPI {

    @GetMapping("/rooms")
    public List<RoomDTO> getRooms() {
        return
                List.of(
                        new RoomDTO(new Room(RoomId.create(), "Salon")),
                        new RoomDTO(new Room(RoomId.create(), "Cave"))
                );
    }
}
