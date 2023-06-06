package com.eora21.request_api_helper.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GitEmailDto {
    private String email;
    private Boolean primary;
    private Boolean verified;
    private String visibility;
}
