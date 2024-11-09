import {useNavigate} from 'react-router-dom';

function LeftMenu() {

    const navigate = useNavigate();

    return (
            <div className="left-menu">
                <button onClick={() => navigate('/')} className="left-menu-btn home-icon">Accueil</button>
                <button onClick={() => navigate('/rooms')} className="left-menu-btn place-icon">Pièces</button>
                <button onClick={() => navigate('/cabinets')} className="left-menu-btn cabinet-icon">Rangements</button>
            </div>
    );
}

export default LeftMenu;