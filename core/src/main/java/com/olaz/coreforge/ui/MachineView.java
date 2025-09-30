package com.olaz.coreforge.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.olaz.coreforge.blocks.Machine;
import com.olaz.coreforge.blocks.machines.ui.MachineUI;

public class MachineView extends Table {
    private final MachineUIManager uiManager;

    public MachineView(MachineUIManager uiManager) {
        this.uiManager = uiManager;
    }

    public void setMachine(Machine machine) {
        clearChildren();

        MachineUI machineUI = uiManager.getMachineUI(machine);
        if (machineUI != null) {
            this.add(machineUI);
        }
    }

    public void removeMachineFromUIMap(Machine machine) {
        uiManager.removeMachine(machine);
    }
}
