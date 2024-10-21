package org.kiennguyenfpt.datingapp.services;

import org.kiennguyenfpt.datingapp.dtos.requests.ProfileCreateRequest;
import org.kiennguyenfpt.datingapp.dtos.requests.UpdateProfileRequest;
import org.kiennguyenfpt.datingapp.entities.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProfileService {
    List<Profile> getAllProfiles();
    Profile getProfileByEmail(String email);
    Profile getProfileByUserId(Long userId);
//    Profile getRandomUserProfileExcludingCurrentUser(String email);
    List<Profile> getAllProfilesExcludingCurrentUserAndSwiped(String email);
}
