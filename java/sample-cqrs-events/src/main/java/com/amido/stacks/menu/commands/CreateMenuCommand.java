package com.amido.stacks.menu.commands;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateMenuCommand extends MenuCommand {

  private static final int OPERATION_CODE = 101;

  private String name;
  private String description;
  private UUID restaurantId;
  private Boolean enabled;

  public CreateMenuCommand(
      String correlationId, String name, String description, UUID restaurantId, Boolean enabled) {
    super(correlationId, null);
    this.name = name;
    this.description = description;
    this.restaurantId = restaurantId;
    this.enabled = enabled;
  }


  @Override
  public int getOperationCode() {
    return OPERATION_CODE;
  }
}
