package org.kiennguyenfpt.datingapp.services;

import org.kiennguyenfpt.datingapp.dtos.responses.SwipeResponse;
import org.kiennguyenfpt.datingapp.entities.Profile;

import java.util.List;

public interface SwipeService {
    SwipeResponse swipe(Long userId, Long targetUserId, boolean isLike);

    List<Profile> getAllLikedProfilesExcludingCurrentUser(Long userId);

}
