import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import {IconButton} from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import {useEffect, useState} from "react";
import {RoomDetails} from "./models/RoomDetails.ts";
import AddCabinet from "./AddCabinet.tsx";
import {CabinetDetail} from "./models/CabinetDetail.ts";

function CabinetsDashboard() {

    const [rooms, setRooms] = useState([] as RoomDetails[]);
    const BASE_API_URL = 'http://localhost:8080/api';
    const ROOMS_SUBPATH = '/rooms';
    const CABINETS_SUBPATH = '/cabinets';

    useEffect(() => {
        fetchRoomDetails();
    }, [])

    function fetchRoomDetails() {
        fetch(BASE_API_URL + ROOMS_SUBPATH + '/details')
            .then(response => response.json())
            .then(data => setRooms(data));
    }

    function handleDelete(cabinetId: string) {
        fetch(BASE_API_URL + CABINETS_SUBPATH + '/' + cabinetId, {
            method: 'DELETE'
        }).then(() => {
            fetchRoomDetails();
        });
    }

    function mapRoomDetailsToCabinets(roomDetails: RoomDetails[]): CabinetDetail[] {
        return roomDetails.flatMap(room =>
            room.cabinets.map(cabinet => ({
                id: cabinet.id,
                name: cabinet.name,
                roomId: room.id,
                roomName: room.name
            })));
    }

    return (
        <div className="add-component">
            <div className="main-component-title">Rangements</div>
            <TableContainer component={Paper} className="styled-table">
                <Table aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Nom</TableCell>
                            <TableCell>Pièce</TableCell>
                            <TableCell></TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {mapRoomDetailsToCabinets(rooms).map((row) => (
                            <TableRow key={row.name}>
                                <TableCell>{row.name}</TableCell>
                                <TableCell>{row.roomName}</TableCell>
                                <TableCell style={{width: '5%'}}>
                                    <IconButton onClick={() => handleDelete(row.id)}>
                                        <DeleteIcon/>
                                    </IconButton>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <AddCabinet rooms={rooms} onAdd={fetchRoomDetails}/>
        </div>
    );
}

export default CabinetsDashboard;