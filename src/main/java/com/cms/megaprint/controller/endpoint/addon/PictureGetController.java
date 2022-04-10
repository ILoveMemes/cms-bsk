package com.cms.megaprint.controller.endpoint.addon;

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
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
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
    ResponseEntity<byte[]> getPicture(@PathVariable String id) {
        Optional<Picture> pic = Optional.empty();
        try {
            Long lId = Long.valueOf(id).longValue();
            pic = pictureService.findById(lId);
        }
        catch (Exception ex) {
            // do nothing. blank image will return
        }
        return pic.map(picture -> ResponseEntity.ok()
                    .contentLength(picture.getData().length)
                    .contentType(MediaType.parseMediaType(new MimetypesFileTypeMap().getContentType(picture.getName())))
                    .body(picture.getData()))
                .orElse(ResponseEntity.ok()
                     .contentLength(blankImage.contentLength())
                     .contentType(MediaType.IMAGE_PNG)
                     .body(IOUtils.toByteArray(blankImage.getInputStream())));
    }

    @SneakyThrows
    @GetMapping("/sm/{id}")
    public @ResponseBody ResponseEntity<byte[]> getSmallPicture(@PathVariable String id) {
        Optional<Picture> pic = Optional.empty();
        try {
            Long lId = Long.valueOf(id).longValue();
            pic = pictureService.findById(lId);
        }
        catch (Exception ex) {
            // do nothing. blank image will return
        }
        int smLength = 0;
        byte[] smData = new byte[1];
        if (pic.isPresent()) {
            InputStream inputStream = new ByteArrayInputStream(pic.get().getData());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByMIMEType(
                    MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(pic.get().getName()));
            if (!imageWriters.hasNext())
                throw new IllegalStateException("Writers Not Found!!");

            ImageWriter imageWriter = (ImageWriter) imageWriters.next();
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            imageWriter.setOutput(imageOutputStream);

            ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();

            imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            imageWriteParam.setCompressionQuality(0.05f);

            imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);

            smData = outputStream.toByteArray();

            inputStream.close();
            outputStream.close();
            imageOutputStream.close();
            imageWriter.dispose();
        }
        byte[] finalSmData = smData;
        return pic.map(picture -> ResponseEntity.ok()
                .contentLength(finalSmData.length)
                .contentType(MediaType.IMAGE_JPEG)
                .body(finalSmData))
                .orElse(ResponseEntity.ok()
                        .contentLength(blankImage.contentLength())
                        .contentType(MediaType.IMAGE_PNG)
                        .body(IOUtils.toByteArray(blankImage.getInputStream())));
    }

}
