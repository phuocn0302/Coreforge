package com.olaz.coreforge.blocks.machines.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.olaz.coreforge.blocks.IOMachineEvent;
import com.olaz.coreforge.blocks.machines.ui.components.InputSlot;
import com.olaz.coreforge.blocks.machines.ui.components.OutputSlot;

public class IOMachineUI extends MachineUI {
    private final Array<InputSlot> inputSlots = new Array<>();
    private final Array<OutputSlot> outputSlots = new Array<>();

    public IOMachineUI(int numInputs, int numOutputs) {
        Table outputRow = new Table();

        for (int i = 0; i < numOutputs; i++) {
            OutputSlot slot = new OutputSlot();
            outputSlots.add(slot);
            outputRow.add(slot).pad(4);
        }

        this.add(outputRow).center().row();

        Table inputRow = new Table();
        for (int i = 0; i < numInputs; i++) {
            InputSlot slot = new InputSlot();
            inputSlots.add(slot);
            inputRow.add(slot).pad(4);
        }
        add(inputRow).center().row();
    }

    public Array<InputSlot> getInputSlots() {
        return inputSlots;
    }


    public void updateInputSlot(IOMachineEvent.InputAdd event) {
        inputSlots.get(event.slot).addResource(event.entry);
    }

    public void updateInputSlot(IOMachineEvent.InputRemove event) {
        inputSlots.get(event.slot).removeResource();
    }

    public void updateOutputSlot(IOMachineEvent.OutputCreated event) {
        outputSlots.get(event.slot).addResource(event.entry);
    }

    public void updateOutputSlot(IOMachineEvent.OutputRemove event) {
        outputSlots.get(event.slot).removeResource();
    }
}
