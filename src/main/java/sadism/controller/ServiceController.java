package sadism.controller;

import com.netflix.discovery.converters.Auto;
import common.entity.RestfulResult;
import common.utils.CommonUtils;
import common.utils.SystemParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sadism.entity.FileResult;
import sadism.entity.ServiceInfo;
import sadism.service.FileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "file")
public class ServiceController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "upload")
    public void upload(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {
        RestfulResult restfulResult = new RestfulResult();
        try {
            FileResult result = fileService.saveFile(file);
            if (! result.success) {
                restfulResult.setResult("Error");
            }else{
                restfulResult.setData(SystemParams.params.get("FILE_SERVER_URL") + result.getFileName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        CommonUtils.printDataJson(response, restfulResult);
    }

    @RequestMapping(value = "download")
    public void download(HttpServletRequest request, HttpServletResponse response, String name) {
        FileResult result = fileService.acquireFile(name);
        CommonUtils.outputFile(response, result.getFile());
    }

    @RequestMapping(value = "rest")
    public String rest(@RequestBody ServiceInfo serviceInfo) {
        return "Service1: Welcome " + serviceInfo.getName() + " !";
    }
}
