package io.inholland.groep4.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Transaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-24T18:28:14.004Z[GMT]")


public class Transaction   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("dateTime")
  private OffsetDateTime dateTime = null;

  @JsonProperty("sender")
  private String sender = null;

  @JsonProperty("reciever")
  private String reciever = null;

  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("description")
  private String description = null;

  public Transaction id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(example = "99999", required = true, description = "")
      @NotNull

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Transaction dateTime(OffsetDateTime dateTime) {
    this.dateTime = dateTime;
    return this;
  }

  /**
   * Get dateTime
   * @return dateTime
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OffsetDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(OffsetDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public Transaction sender(String sender) {
    this.sender = sender;
    return this;
  }

  /**
   * Get sender
   * @return sender
   **/
  @Schema(example = "NL91 ABNA 0417 1643 00", required = true, description = "")
      @NotNull

    public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public Transaction reciever(String reciever) {
    this.reciever = reciever;
    return this;
  }

  /**
   * Get reciever
   * @return reciever
   **/
  @Schema(example = "NL91 ABNA 0417 1643 00", required = true, description = "")
      @NotNull

    public String getReciever() {
    return reciever;
  }

  public void setReciever(String reciever) {
    this.reciever = reciever;
  }

  public Transaction amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * minimum: 0
   * maximum: 1000000
   * @return amount
   **/
  @Schema(example = "12345.67", required = true, description = "")
      @NotNull

  @DecimalMin("0") @DecimalMax("1000000")   public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Transaction description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @Schema(example = "Rolex", required = true, description = "")
      @NotNull

    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.id, transaction.id) &&
        Objects.equals(this.dateTime, transaction.dateTime) &&
        Objects.equals(this.sender, transaction.sender) &&
        Objects.equals(this.reciever, transaction.reciever) &&
        Objects.equals(this.amount, transaction.amount) &&
        Objects.equals(this.description, transaction.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dateTime, sender, reciever, amount, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    dateTime: ").append(toIndentedString(dateTime)).append("\n");
    sb.append("    sender: ").append(toIndentedString(sender)).append("\n");
    sb.append("    reciever: ").append(toIndentedString(reciever)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
