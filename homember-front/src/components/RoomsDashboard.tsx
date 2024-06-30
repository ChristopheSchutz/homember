import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import DeleteIcon from '@mui/icons-material/Delete';
import {useEffect, useState} from "react";
import {RoomDetails} from "./models/RoomDetails.ts";
import AddRoom from "./AddRoom.tsx";
import {IconButton} from "@mui/material";

function RoomsDashboard() {

    const [rooms, setRooms] = useState([] as RoomDetails[]);
    const BASE_API_URL = 'http://localhost:8080/api';
    const ROOMS_SUBPATH = '/rooms';

    function fetchRoomDetails() {
        fetch(BASE_API_URL + ROOMS_SUBPATH + '/details')
            .then(response => response.json())
            .then(data => setRooms(data));
    }

    function handleDelete(roomId: string) {
        fetch(BASE_API_URL + ROOMS_SUBPATH + '/' + roomId, {
            method: 'DELETE'
        }).then(() => {
            fetchRoomDetails();
        });
    }

    useEffect(() => {
        fetchRoomDetails();
    }, [])

    return (
        <div className="add-component">
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
                            <TableRow key={row.name}>
                                <TableCell>{row.name}</TableCell>
                                <TableCell>{row.cabinets.length}</TableCell>
                                <TableCell style={{width: '5%'}}>
                                    <IconButton onClick={() => handleDelete(row.id)}
                                                disabled={row.cabinets.length != 0}>
                                        <DeleteIcon/>
                                    </IconButton>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <AddRoom onAdd={fetchRoomDetails}/>
        </div>
    );
}

export default RoomsDashboard;