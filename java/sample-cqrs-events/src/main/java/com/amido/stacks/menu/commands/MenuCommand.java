package com.amido.stacks.menu.commands;

import com.amido.stacks.core.cqrs.command.ApplicationCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MenuCommand extends ApplicationCommand {
  private UUID menuId;

  private static final int OPERATION_CODE = 104;

  public MenuCommand(String correlationId, UUID menuId) {
    super(correlationId);
    this.menuId = menuId;
  }

  @Override
  public int getOperationCode() {
    return OPERATION_CODE;
  }
}
