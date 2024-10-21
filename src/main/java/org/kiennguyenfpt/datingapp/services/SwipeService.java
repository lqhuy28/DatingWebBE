package org.kiennguyenfpt.datingapp.services;

import org.kiennguyenfpt.datingapp.dtos.responses.SwipeResponse;

public interface SwipeService {
    SwipeResponse swipe(Long userId, Long targetUserId, boolean isLike);
}
