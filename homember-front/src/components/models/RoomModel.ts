import {CabinetModel} from "./CabinetModel.ts";

export interface RoomModel {
    id: string;
    name: string;
    cabinets: Array<CabinetModel>;
}