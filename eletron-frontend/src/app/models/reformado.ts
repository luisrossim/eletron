import { Marca } from "./marca";
import { TipoAparelho } from "./tipoAparelho";

export interface Reformado {
    id?: number;
    tipoAparelho?: TipoAparelho;
    marca?: Marca;
    modelo?: string;
    valor?: number;
    criadoEm?: string;
    atualizadoEm?: string;
}