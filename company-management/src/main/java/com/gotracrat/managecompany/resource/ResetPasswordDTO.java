package com.gotracrat.managecompany.resource;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto associated with reset password request.
 * @author Manikanta
 */
@Getter
@Setter
public class ResetPasswordDTO {
    private String password;
    private String resetToken;
}
