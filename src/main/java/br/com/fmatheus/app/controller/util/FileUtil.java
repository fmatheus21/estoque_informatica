package br.com.fmatheus.app.controller.util;

import jakarta.validation.constraints.NotNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
public class FileUtil {

    private FileUtil() {
        throw new IllegalStateException(getClass().getName());
    }


    public static File saveFile(MultipartFile file, String path, String fileName) throws IOException {

        log.info("Iniciando o processo de ssalvamente do arquivo.");
        var filesPath = Paths.get(path);

        if (Files.notExists(filesPath)) {
            log.info("O diretorio nao existe e sera criado.");
            Files.createDirectories(filesPath);
        }
        var filePath = filesPath.resolve(fileName.concat(returnsFileExtension(file)));
        file.transferTo(filePath.toFile());

        var savedFile = filePath.toFile().getAbsoluteFile();

        log.info("O arquivo foi salvo no diretorio {}", savedFile);

        return savedFile;

    }

    public static String returnsFileExtension(@NotNull MultipartFile file) {
        var originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        return "";
    }


    /**
     * Procura o arquivo em um diretorio e retorna em um InputStream.
     * filter(Files::isRegularFile): Filtra apenas arquivos regulares.
     * filter(path -> path.getFileName().toString().equals(fileName)): Verifica o nome do arquivo.
     * findFirst(): Retorna o primeiro arquivo encontrado
     *
     * @param directoryPath Diretorio do arquivo
     * @param fileName      Nome do arquivo
     * @return {@link Optional<InputStream>}
     * @throws IOException Excecao lancada
     */

    public static Optional<InputStream> findFileAsInputStream(String directoryPath, String fileName) throws IllegalArgumentException, UnsupportedOperationException, IOException {
        Path dirPath = Paths.get(directoryPath);

        if (!Files.isDirectory(dirPath)) {
            throw new IllegalArgumentException("O caminho fornecido não é um diretório válido: " + directoryPath);
        }

        log.info("Diretorio {} encontrado. ", directoryPath);
        log.info("Procurando pelo arquivo {}.", fileName);

        try (var paths = Files.walk(dirPath)) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().equals(fileName))
                    .findFirst()
                    .map(path -> {
                        try {
                            return Files.newInputStream(path);
                        } catch (IOException e) {
                            throw new UncheckedIOException("Erro ao abrir o arquivo: " + path, e);
                        }
                    });
        }
    }

}
