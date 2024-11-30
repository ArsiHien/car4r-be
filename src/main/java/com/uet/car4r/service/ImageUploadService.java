package com.uet.car4r.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageUploadService {

    private final Cloudinary cloudinary;

    public String uploadImage(MultipartFile file) throws IOException {
        try {
            String uniquePublicId = "car_image_" + System.currentTimeMillis();
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap(
                            "public_id", uniquePublicId,
                            "use_filename", true,
                            "unique_filename", false,
                            "overwrite", false
                    ));
            return uploadResult.get("url").toString();
        } catch (IOException e) {
            throw new IOException("Image upload failed: " + e.getMessage());
        }
    }
}
