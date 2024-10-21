package org.kiennguyenfpt.datingapp.repositories;

import org.kiennguyenfpt.datingapp.dtos.responses.AdminMatchResponse;
import org.kiennguyenfpt.datingapp.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByUser1_UserIdOrUser2_UserId(Long user1Id, Long user2Id);

    boolean existsByUser1_UserIdAndUser2_UserId(Long user1Id, Long user2Id);

    @Query(value = "SELECT m.match_id, u1.email AS user1_email, u2.email AS user2_email, p1.name AS user1_name, p2.name AS user2_name, m.created_at " +
            "FROM matches m " +
            "JOIN users u1 ON m.user1_id = u1.user_id " +
            "JOIN users u2 ON m.user2_id = u2.user_id " +
            "LEFT JOIN profiles p1 ON u1.user_id = p1.user_id " +
            "LEFT JOIN profiles p2 ON u2.user_id = p2.user_id",
            nativeQuery = true)
    List<Object[]> getAllMatchesWithUserDetailsRaw();

    default List<AdminMatchResponse> getAllMatchesWithUserDetails() {
        List<Object[]> results = getAllMatchesWithUserDetailsRaw();
        return results.stream()
                .map(result -> new AdminMatchResponse(
                        (Long) result[0],  // match_id
                        (String) result[1], // user1_email
                        (String) result[2], // user2_email
                        (String) result[3], // user1_name
                        (String) result[4], // user2_name
                        ((Timestamp) result[5]).toLocalDateTime() // created_at
                ))
                .collect(Collectors.toList());
    }

}
