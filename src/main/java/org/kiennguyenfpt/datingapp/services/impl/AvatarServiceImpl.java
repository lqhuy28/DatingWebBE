package org.kiennguyenfpt.datingapp.services.impl;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.kiennguyenfpt.datingapp.entities.Profile;
import org.kiennguyenfpt.datingapp.services.AvatarService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class AvatarServiceImpl implements AvatarService {
    @Override
    public String uploadAvatar(Profile profile, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        Bucket bucket = StorageClient.getInstance().bucket();
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        Blob blob = bucket.create(fileName, file.getBytes(), file.getContentType());
        return blob.getMediaLink();
    }

    @Override
    public String selectAvatarFromExisting(Profile profile, String imageUrl) {
        return "";
    }
}
