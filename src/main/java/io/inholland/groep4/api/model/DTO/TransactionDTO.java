package io.inholland.groep4.api.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class TransactionDTO {

    @Schema(example = "NL91 ABNA 0417 1643 00", required = true, description = "")
    @NotNull
    private String sender;

    @Schema(example = "NL91 ABNA 0417 1643 00", required = true, description = "")
    @NotNull
    private String receiver;

    @Schema(example = "12345.67", required = true, description = "")
    @NotNull
    private double amount;

    @Schema(example = "Rolex", required = true, description = "")
    @NotNull
    private String description;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
