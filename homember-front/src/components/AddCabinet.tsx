import {Button, FormControl, InputLabel, MenuItem, Select, SelectChangeEvent, TextField} from "@mui/material";
import {useState} from "react";
import {RoomDetails} from "./models/RoomDetails.ts";
import {OnAdd} from "./models/Types.ts";

function AddCabinet({rooms, onAdd}: { rooms: RoomDetails[], onAdd: OnAdd }) {
    const [newCabinetName, setNewCabinetName] = useState('');
    const [newCabinetRoomId, setNewCabinetRoomId] = useState('');
    const BASE_API_URL = 'http://localhost:8080/api';
    const CABINETS_SUBPATH = '/cabinets';

    function handleChangeRoom(selectedRoom: SelectChangeEvent) {
        setNewCabinetRoomId(selectedRoom.target.value);
    }

    function handleAdd() {
        fetch(BASE_API_URL + CABINETS_SUBPATH, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: newCabinetName,
                roomId: newCabinetRoomId
            })
        }).then(() => {
            setNewCabinetName('');
            onAdd();
        });
    }

    function isDisabled() {
        return newCabinetName === '' || newCabinetRoomId === ''
    }

    return (
        <div id="add-cabinet" className="add-form">
            <div id="add-cabinet-title" className="add-form-title"><h2>Ajouter un rangement</h2></div>
            <TextField id="add-name-value" label="Nom" variant="outlined" className='add-name-tf' value={newCabinetName}
                       onChange={e => setNewCabinetName(e.target.value)}/>
            <FormControl>
                <InputLabel>Pièce</InputLabel>
                <Select id="place-id" label="Pièce" onChange={handleChangeRoom} value={newCabinetRoomId}
                        variant="outlined">
                    {
                        rooms.map(place => <MenuItem key={place.id} value={place.id}>{place.name}</MenuItem>)
                    }
                </Select>
            </FormControl>
            <Button variant='contained' onClick={() => handleAdd()} disabled={isDisabled()}
                    className='add-button'>Ajouter</Button>
        </div>
    )
}

export default AddCabinet