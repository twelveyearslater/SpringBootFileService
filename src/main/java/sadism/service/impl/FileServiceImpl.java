package sadism.service.impl;

import common.utils.SystemParams;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sadism.entity.FileResult;
import sadism.service.FileService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public FileResult saveFile(MultipartFile file) {
        FileResult fileResult = new FileResult();
        boolean linux = Boolean.parseBoolean("" + SystemParams.params.get("OS_LINUX"));
        String path = linux ? "" + SystemParams.params.get("FILE_PATH_FOR_LINUX") : "" + SystemParams.params.get("FILE_PATH_FOR_WINDOWS");
        String uuid = UUID.randomUUID().toString();
        System.out.println(file.getName());
        boolean nameContainPoint = file.getOriginalFilename().lastIndexOf(".") > -1;
        String fileName = nameContainPoint ? uuid + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) : uuid;
        try {
            File newFile = new File(path + fileName);
            file.transferTo(newFile);
        } catch (IOException e) {
            fileResult.setSuccess(false);
            return fileResult;
        }
        fileResult.setUuid(uuid);
        return fileResult;
    }

    @Override
    public FileResult acquireFile(String id) {
        return null;
    }
}
