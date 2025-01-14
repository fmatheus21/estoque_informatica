package br.com.fmatheus.app.controller.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@RequiredArgsConstructor
@Component
public class ConverterUtil {

    private final XmlMapper xmlMapper;
    private final ObjectMapper objectMapper;

    /**
     * @param json  Json em formato de string
     * @param clazz Classe alvo para desserializacao
     * @param <T>   Tipo generico
     * @return Objeto desserializado
     */
    public <T> T convertJsonToObject(String json, Class<T> clazz) throws JsonProcessingException {
        return this.objectMapper.readValue(json, clazz);
    }

    /**
     * Metodo generico para desserializar XML em qualquer classe
     *
     * @param inputStream InputStream do XML
     * @param clazz       Classe alvo para desserializacao
     * @param <T>         Tipo generico
     * @return Objeto desserializado
     */
    public <T> T convertXmlToObject(InputStream inputStream, Class<T> clazz) throws IOException {
        return this.xmlMapper.readValue(inputStream, clazz);
    }

    /**
     * Le o conteudo do InputStream e converte em uma String formatada.
     *
     * @param file O arquivo recebido como InputStream
     * @return String contendo o conteudo do arquivo formatado
     */
    public String convertXmlToString(File file) throws IOException {
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        return new String(fileBytes, StandardCharsets.UTF_8);
    }

}
