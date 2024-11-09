package com.rossim.eletron.Deserializer;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rossim.eletron.Model.State.*;
import com.rossim.eletron.Model.StatusServico;

import java.io.IOException;

public class StatusServicoDeserializer extends JsonDeserializer<StatusServico> {
    @Override
    public StatusServico deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode node = mapper.readTree(jp);

        Long id = node.get("id").asLong();

        return switch (id.intValue()) {
            case 1 -> new Recebido();
            case 2 -> new AguardandoPecas();
            case 3 -> new EmExecucao();
            case 4 -> new Finalizado();
            case 5 -> new Cancelado();
            case 6 -> new Vendido();
            default -> throw new IllegalArgumentException("Status ID inválido: " + id);
        };
    }
}
