package br.com.fmatheus.app.controller.helper;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class XmlHelper {

    private final XmlMapper xmlMapper;

    /**
     * Metodo generico para desserializar XML em qualquer classe
     *
     * @param inputStream InputStream do XML
     * @param clazz Classe alvo para desserializacao
     * @param <T> Tipo generico
     * @return Objeto desserializado
     * @throws IOException Caso ocorra erro ao ler o XML
     */
    public <T> T parseXml(InputStream inputStream, Class<T> clazz) throws IOException {
        return xmlMapper.readValue(inputStream, clazz);
    }
}


