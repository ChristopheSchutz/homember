import {Routes, Route} from 'react-router-dom';
import RoomComponent from "./RoomComponent.tsx";
import HomeComponent from "./HomeComponent.tsx";
import CabinetComponent from "./CabinetComponent.tsx";

function MainContent() {
    return (
        <>
            <div className="main-content">
                <Routes>
                    <Route path="/" element={<HomeComponent/>}/>
                    <Route path="/rooms" element={<RoomComponent/>}/>
                    <Route path="/cabinets" element={<CabinetComponent/>}/>
                    <Route path="*" element={<HomeComponent/>}/>
                </Routes>
            </div>
        </>
    );
}

export default MainContent;