package org.kiennguyenfpt.datingapp.repositories;

import org.kiennguyenfpt.datingapp.entities.Swipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwipeRepository extends JpaRepository<Swipe, Long> {
    Swipe findByUser_UserIdAndTargetUser_UserId(Long userId, Long targetUserId);
}
