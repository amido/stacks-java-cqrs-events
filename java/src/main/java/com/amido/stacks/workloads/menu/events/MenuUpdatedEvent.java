package com.amido.stacks.workloads.menu.events;

import com.amido.stacks.workloads.menu.commands.MenuCommand;

public class MenuUpdatedEvent extends MenuEvent {

  private static final int EVENT_CODE = 102;

  public MenuUpdatedEvent(MenuCommand command) {
    super(command);
  }

  @Override
  public int getEventCode() {
    return EVENT_CODE;
  }
}
