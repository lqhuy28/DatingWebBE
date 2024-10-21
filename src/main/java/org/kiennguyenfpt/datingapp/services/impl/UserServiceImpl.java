package org.kiennguyenfpt.datingapp.services.impl;

import org.kiennguyenfpt.datingapp.dtos.requests.UpdateProfileRequest;
import org.kiennguyenfpt.datingapp.entities.Photo;
import org.kiennguyenfpt.datingapp.entities.Profile;
import org.kiennguyenfpt.datingapp.entities.User;
import org.kiennguyenfpt.datingapp.repositories.UserRepository;
import org.kiennguyenfpt.datingapp.services.PhotoService;
import org.kiennguyenfpt.datingapp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PhotoService photoService;

    public UserServiceImpl(UserRepository userRepository, PhotoService photoService) {
        this.userRepository = userRepository;
        this.photoService = photoService;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> searchUsers(String keyword) {
        return userRepository.searchUsersByKeyword(keyword);
    }

    @Override
    public int lockOrUnLockUser(Long id) {
        return userRepository.lockOrUnLockUser(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.getReferenceById(id);
    }

    /*
    @Override
    public User updateProfile(String email, UpdateProfileRequest updateProfileRequest, List<MultipartFile> files) throws IOException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            Profile profile = user.getProfile();
            if (profile == null) {
                profile = new Profile();
                profile.setUser(user);
                user.setProfile(profile);
            }

            // Require full update on second login
            if (user.getLoginCount() == 1) {
                if (updateProfileRequest.getName() == null || updateProfileRequest.getAge() == null
                        || updateProfileRequest.getBio() == null || updateProfileRequest.getGender() == null
                        || updateProfileRequest.getPhone() == null) {
                    throw new IllegalArgumentException("All fields must be provided for the second login update.");
                }
            }

            // Update profile fields
            profile.setName(updateProfileRequest.getName());
            profile.setAge(updateProfileRequest.getAge());
            profile.setBio(updateProfileRequest.getBio());
            profile.setGender(updateProfileRequest.getGender());
            profile.setAvatar(updateProfileRequest.getAvatar());
            profile.setPhone(updateProfileRequest.getPhone());

            // Save the profile first to avoid transient property exception
            userRepository.save(user);

            // Handle photo uploads
            List<String> imageUrls = photoService.uploadPhotos(email, files);
            if (!imageUrls.isEmpty()) {
                profile.setAvatar(imageUrls.get(0));
            }
            List<Photo> photos = photoService.getPhotos(profile.getProfileId());
            for (Photo photo : photos) {
                photo.setProfile(profile); // Ensure the profile field is set
                photoService.savePhoto(photo); // Save each photo to persist changes
            }

            // Update the existing photos list instead of replacing it
            profile.getPhotos().clear();
            profile.getPhotos().addAll(photos);

            // Save the user again to persist changes
            userRepository.save(user);
            return user;
        }
        return null;
    }

     */
    @Override
    public User updateProfile(String email, UpdateProfileRequest updateProfileRequest, List<MultipartFile> files) throws IOException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            Profile profile = user.getProfile();
            if (profile == null) {
                profile = new Profile();
                profile.setUser(user);
                user.setProfile(profile);
            }

            // Update profile fields
            profile.setName(updateProfileRequest.getName());
            profile.setAge(updateProfileRequest.getAge());
            profile.setBio(updateProfileRequest.getBio());
            profile.setGender(updateProfileRequest.getGender());
            profile.setAvatar(updateProfileRequest.getAvatar());
            profile.setPhone(updateProfileRequest.getPhone());

            // Save the profile first to avoid transient property exception
            userRepository.save(user);

            // Handle photo uploads
            List<String> imageUrls = photoService.uploadPhotos(email, files);
            if (!imageUrls.isEmpty()) {
                profile.setAvatar(imageUrls.get(0));
            }
            List<Photo> photos = photoService.getPhotos(profile.getProfileId());
            for (Photo photo : photos) {
                photo.setProfile(profile); // Ensure the profile field is set
                photoService.savePhoto(photo); // Save each photo to persist changes
            }

            // Update the existing photos list instead of replacing it
            profile.getPhotos().clear();
            profile.getPhotos().addAll(photos);

            // Save the user again to persist changes
            userRepository.save(user);
            return user;
        }
        return null;
    }


    @Override
    public void updateAvatar(User user, String imageUrl) {
        user.getProfile().setAvatar(imageUrl);
        userRepository.save(user);
    }

}
