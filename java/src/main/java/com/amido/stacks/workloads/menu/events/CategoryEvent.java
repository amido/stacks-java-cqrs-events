package com.amido.stacks.workloads.menu.events;

import com.amido.stacks.workloads.menu.commands.MenuCommand;
import java.util.UUID;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class CategoryEvent extends MenuEvent {
  private final UUID categoryId;

  protected CategoryEvent(MenuCommand menuCommand, UUID categoryId) {
    super(menuCommand, menuCommand.getMenuId());
    this.categoryId = categoryId;
  }
}
