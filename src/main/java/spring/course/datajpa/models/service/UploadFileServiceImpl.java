package spring.course.datajpa.models.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final static String UPLOADS_DIR = "uploads";

    public Path getPath(String filename) {
        return Paths.get(UPLOADS_DIR).resolve(filename).toAbsolutePath();
    }

    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path pathphoto = getPath(filename);
        log.info("pathPhoto " + pathphoto);
        Resource resource = null;

        resource = new UrlResource(pathphoto.toUri());
        if (!resource.exists() && !resource.isReadable()) {
            throw new RuntimeException("Error: no se puede cargar la imagen: " + pathphoto.toString());

        }
        return resource;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path rootPath = getPath(uniqueFilename);

        log.info("rootPath: " + rootPath);

        Files.copy(file.getInputStream(), rootPath);

        return uniqueFilename;
    }

    @Override
    public boolean delete(String filename) {
        Path rootPath = getPath(filename);
        File file = rootPath.toFile();

        if (file.exists() && file.canRead()) {
            if (file.delete()) {
                return true;
            }
        }
        return false;
    }

}


