package org.kiennguyenfpt.datingapp.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kiennguyenfpt.datingapp.entities.Photo;
import org.kiennguyenfpt.datingapp.enums.Gender;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private long profileId;
    private long userId;
    private String name;
    private Integer age;
    private Gender gender;
    private String bio;
    private String avatar;
    private String phone;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<Photo> photos;
}
