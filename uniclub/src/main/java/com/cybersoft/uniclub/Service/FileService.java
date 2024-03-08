package com.cybersoft.uniclub.Service;

import com.cybersoft.uniclub.Service.Imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Service
public class FileService implements FileServiceImp {

    @Value("${upload.file.path}")
    private String path;
    @Override
    public void saveFile(MultipartFile file) {
        try {
            Path root = Paths.get(path);//Dùng để xử lý đường dẫn
            if (!Files.exists(root)) {
                Files.createDirectory(root);
            }

            Files.copy(file.getInputStream(),root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

        }catch (Exception e){
            throw new RuntimeException("Loi luu file" + e.getMessage());
        }
    }
}
