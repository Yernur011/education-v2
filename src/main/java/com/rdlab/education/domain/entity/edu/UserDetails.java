package com.rdlab.education.domain.entity.edu;

import com.rdlab.education.domain.entity.image.Base64Images;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetails {
    @Id
    Long id;
    String lastname;
    @OneToOne(cascade = CascadeType.ALL)
    Base64Images images;
}
