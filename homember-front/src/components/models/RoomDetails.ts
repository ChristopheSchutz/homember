import {CabinetModel} from "./CabinetModel.ts";

export interface RoomDetails {
    id: string;
    name: string;
    cabinets: Array<CabinetModel>;
}