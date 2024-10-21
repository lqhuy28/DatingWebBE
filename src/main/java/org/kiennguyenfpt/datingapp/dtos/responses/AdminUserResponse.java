package org.kiennguyenfpt.datingapp.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kiennguyenfpt.datingapp.entities.Role;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserResponse {
    private long userId;

    private String email;

    private String phone;

    private Timestamp createdAt;

    private String status;

    private Role role;
    
}
