package org.kiennguyenfpt.datingapp.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminMatchResponse {
    private Long matchId;
    private String user1Email;
    private String user2Email;
    private String user1Name;
    private String user2Name;
    private LocalDateTime createdAt;

}
