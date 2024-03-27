import {RoomModel} from "./models/RoomModel.ts";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import {Button, IconButton, TextField} from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import {useState} from "react";
function RoomComponent() {

    const data: RoomModel[] = [
        {
            id: '1',
            name: 'Salon',
            cabinets: [
                {
                    id: '1', name: 'Bureau', items:[],
                },
                {
                    id: '2', name: 'Garde-Manger', items:[]
                }]
        },
        {
            id: '2',
            name:'Cave',
            cabinets: []
        }
    ];

    const [rooms, setRooms] = useState(data);
    const [newRoomName, setNewRoomName] = useState('');

    const handleDelete = (roomId: string) => {
        console.log(`Delete ${roomId}`);
        console.log(rooms);
        const newRooms = rooms.filter(room => room.id !== roomId);
        setRooms(newRooms);
    };


    const handleAdd = () => {
        console.log('Add a room named ' + newRoomName);
        setNewRoomName('');
        rooms.push({
            id: '3',
            name: newRoomName,
            cabinets: []
        });
    }

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