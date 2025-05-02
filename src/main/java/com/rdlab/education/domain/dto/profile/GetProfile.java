package com.rdlab.education.domain.dto.profile;

import java.util.List;

public record GetProfile(String firstname,
                         String lastname,
                         String image,
                         String role,
                         List<Long> topics) {
}
