package com.rentahome.image;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/image")
public class ImageController {
    private final ImageRepository imageRepository;

    @PostMapping
    public Long uploadImageFile(@RequestParam() MultipartFile multipartImage) throws IOException {
        Image image = new Image(
                multipartImage.getName(),
                multipartImage.getName(),
                multipartImage.getSize(),
                multipartImage.getBytes()
        );
        return imageRepository.save(image).getId();
    }

    @GetMapping(path = "{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource downloadImage(@PathVariable Long imageId){
        byte[] image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();
        return new ByteArrayResource(image);
    }
}
