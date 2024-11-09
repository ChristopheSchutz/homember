import {Button, TextField} from "@mui/material";
import {useState} from "react";
import {OnAdd} from "./models/Types.ts";

function AddRoom({onAdd}: { onAdd: OnAdd }) {
    const [newRoomName, setNewRoomName] = useState('');
    const BASE_API_URL = 'http://localhost:8080/api';
    const ROOMS_SUBPATH = '/rooms';

    function isDisabled() {
        return newRoomName === ''
    }

    function handleAdd() {
        fetch(BASE_API_URL + ROOMS_SUBPATH, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: newRoomName
            })
        }).then(() => {
            setNewRoomName('');
            onAdd();
        });
    }

    return (
        <div id="add-place" className="add-form">
            <div id="add-place-title" className="add-form-title"><h2>Ajouter une pièce</h2></div>
            <TextField id="add-name-value" label="Nom" variant="outlined" className='add-name-tf' value={newRoomName}
                       onChange={e => setNewRoomName(e.target.value)}/>
            <Button variant='contained' onClick={() => handleAdd()} disabled={isDisabled()}
                    className='add-button'>Ajouter</Button>
        </div>
    )


}

export default AddRoom