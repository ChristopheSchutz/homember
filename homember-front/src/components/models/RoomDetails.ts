import {CabinetModel} from "./CabinetModel.ts";
import {RoomModel} from "./RoomModel.ts";

export interface RoomDetails {
    room: RoomModel;
    cabinets: Array<CabinetModel>;
}