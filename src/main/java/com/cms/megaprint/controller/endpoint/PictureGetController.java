package com.cms.megaprint.controller.endpoint;

import com.cms.megaprint.model.Picture;
import com.cms.megaprint.service.intface.PictureService;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.activation.MimetypesFileTypeMap;
import java.util.Optional;

@Controller
@RequestMapping("/p")
public class PictureGetController {

    private final PictureService pictureService;

    public PictureGetController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @Value("classpath:static/images/blank_image.png")
    Resource blankImage;

    @SneakyThrows
    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<byte[]> getPicture(@PathVariable Long id) {
        Optional<Picture> pic = pictureService.findById(id);
        return pic.map(picture -> ResponseEntity.ok()
                    .contentLength(picture.getData().length)
                    .contentType(MediaType.parseMediaType(new MimetypesFileTypeMap().getContentType(picture.getName())))
                    .body(picture.getData()))
                .orElse(ResponseEntity.ok()
                     .contentLength(blankImage.contentLength())
                     .contentType(MediaType.IMAGE_PNG)
                     .body(IOUtils.toByteArray(blankImage.getInputStream())));
    }

}
