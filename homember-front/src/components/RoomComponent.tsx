import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import {Button, IconButton, TextField} from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import {useEffect, useState} from "react";
import {RoomDetails} from "./models/RoomDetails.ts";

function RoomComponent() {

    const [rooms, setRooms] = useState([] as RoomDetails[]);
    const [newRoomName, setNewRoomName] = useState('');
    const BASE_API_URL = 'http://localhost:8080/api/rooms';

    const handleGetRoomDetails = () => {
        fetch(BASE_API_URL+'/details')
            .then(response => response.json())
            .then(data => setRooms(data));
    };

    const handleDelete = (roomId: string) => {
        fetch( BASE_API_URL + '/' + roomId, {
            method: 'DELETE'
        }).then(() => {
            handleGetRoomDetails();
        });
    };

    const handleAdd = () => {
        fetch( BASE_API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: newRoomName
            })
        }).then(() => {
            setNewRoomName('');
            handleGetRoomDetails();
        });
    }

    useEffect(() => {
        handleGetRoomDetails();
    }, [])

    return (
        <div className="room-component">
            <div className="main-component-title">Pièces</div>
            <TableContainer component={Paper} className="styled-table">
                <Table aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Nom</TableCell>
                            <TableCell>Rangements</TableCell>
                            <TableCell></TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {rooms.map((row) => (
                            <TableRow
                                key={row.name}
                                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                            >
                                <TableCell component="th" scope="row">
                                    {row.name}
                                </TableCell>
                                <TableCell>{row.cabinets.length}</TableCell>
                                <TableCell>
                                    <IconButton onClick={() => handleDelete(row.id)} disabled={row.cabinets.length != 0}>
                                        <DeleteIcon />
                                    </IconButton>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <TextField id="add-name-value" label="Nom" variant="outlined" className='add-name-tf' value={newRoomName} onChange={e => setNewRoomName(e.target.value)}/>
            <Button variant='contained' onClick={() => handleAdd()} className='add-button'>Ajouter</Button>
        </div>
    );
}

export default RoomComponent;