import {Route, Routes} from 'react-router-dom';
import RoomsDashboard from "../RoomsDashboard.tsx";
import HomeComponent from "./HomeComponent.tsx";
import CabinetsDashboard from "../CabinetsDashboard.tsx";

function MainContent() {
    return (
        <>
            <div className="main-content">
                <Routes>
                    <Route path="/" element={<HomeComponent/>}/>
                    <Route path="/rooms" element={<RoomsDashboard/>}/>
                    <Route path="/cabinets" element={<CabinetsDashboard/>}/>
                    <Route path="*" element={<HomeComponent/>}/>
                </Routes>
            </div>
        </>
    );
}

export default MainContent;