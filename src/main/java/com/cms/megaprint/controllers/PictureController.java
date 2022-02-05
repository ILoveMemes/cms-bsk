package com.cms.megaprint.controllers;

import com.cms.megaprint.common.CrudController;
import com.cms.megaprint.models.Picture;
import com.cms.megaprint.services.PictureService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pic")
public class PictureController extends CrudController<Picture, Long> {

    public PictureController(PictureService service) {
        super(service);
    }

    @PostMapping("/upload")
    public @ResponseBody boolean uploadPicture(@RequestParam("image") MultipartFile multipartFile) {
        Picture picture = new Picture();
        boolean result = true;
        try {
            picture.setData(multipartFile.getBytes());
            picture.setName(multipartFile.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        service.save(picture);
        return result;
    }

    @GetMapping("/idList")
    public @ResponseBody List<Long> getIdList() {
        return service.findAll().stream().map(Picture::getId).collect(Collectors.toList());
    }

}
