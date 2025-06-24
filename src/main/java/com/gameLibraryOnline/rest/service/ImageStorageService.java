package com.gameLibraryOnline.rest.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class ImageStorageService {

    private final Path storageDir = Paths.get("src/main/resources/static/pictures");

    public String storeImage(MultipartFile file, String title) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Le fichier image est vide.");
        }

        // Crée le dossier s'il n'existe pas
        if (!Files.exists(storageDir)) {
            Files.createDirectories(storageDir);
        }

        // Nettoie le nom original
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = getExtension(originalFilename);
        String safeTitle = slugify(title);

        // Génère un nom unique
        String filename = safeTitle + "_" + UUID.randomUUID().toString().substring(0, 8) + "." + extension;
        Path destinationPath = storageDir.resolve(filename);

        // Sauvegarde
        Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

        return filename; // Ce nom sera stocké dans GamePublic.picture
    }

    private String getExtension(String filename) {
        String ext = StringUtils.getFilenameExtension(filename);
        return ext != null ? ext : "png";
    }

    private String slugify(String input) {
        return input.toLowerCase()
                    .replaceAll("[^a-z0-9]", "-")
                    .replaceAll("-{2,}", "-")
                    .replaceAll("^-|-$", "");
    }
}
