package com.gotracrat.managelocation.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuthToken {
    private String access_token;
    private Integer expires_in;
    private String token_type;
    private String scope;
}
