package io.inholland.groep4.api.model.DTO;

import io.inholland.groep4.api.model.User;
import io.inholland.groep4.api.model.UserAccount;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class UserAccountDTO {

    @Schema(example = "current", required = true, description = "")
    @NotNull
    private UserAccount.AccountTypeEnum accountType;

    @Schema(example = "1", required = true, description = "")
    @NotNull
    private User owner;

    @Schema(example = "100", required = true, description = "")
    @NotNull
    private Double lowerLimit;

    @Schema(example = "active", required = true, description = "")
    @NotNull
    private UserAccount.AccountStatusEnum accountStatus;

    public UserAccount.AccountTypeEnum getAccountType() {
        return accountType;
    }

    public void setAccountType(UserAccount.AccountTypeEnum accountType) {
        this.accountType = accountType;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Double getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public UserAccount.AccountStatusEnum getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(UserAccount.AccountStatusEnum accountStatus) {
        this.accountStatus = accountStatus;
    }
}
