package sadism.service;

import org.springframework.web.multipart.MultipartFile;
import sadism.entity.FileResult;

public interface FileService {

    FileResult saveFile(MultipartFile file);

    FileResult acquireFile(String id);
}
