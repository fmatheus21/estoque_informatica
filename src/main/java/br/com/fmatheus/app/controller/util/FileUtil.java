package br.com.fmatheus.app.controller.util;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

}
