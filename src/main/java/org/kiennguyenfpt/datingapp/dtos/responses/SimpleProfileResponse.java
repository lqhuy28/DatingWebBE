package org.kiennguyenfpt.datingapp.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleProfileResponse {
    private long profileId;
    private Long userId;
    private String avatar;
    private String name;
    private Integer age;
    private String bio;
}
