package br.com.fmatheus.app.controller.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JsonHelper {

    private final ObjectMapper objectMapper;

    /**
     * @param json  Json em formato de string
     * @param clazz Classe alvo para desserializacao
     * @param <T>   Tipo generico
     * @return Objeto desserializado
     * @throws JsonProcessingException Caso ocorra erro ao ler o XML
     */
    public <T> T fromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }
}
