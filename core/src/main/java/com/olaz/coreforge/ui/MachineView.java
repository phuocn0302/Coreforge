package com.olaz.coreforge.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.olaz.coreforge.blocks.IOMachine;
import com.olaz.coreforge.blocks.Machine;
import com.olaz.coreforge.blocks.machines.MachineInput;
import com.olaz.coreforge.blocks.machines.MachineOutput;
import com.olaz.coreforge.blocks.machines.MachineResourceEntry;
import com.olaz.coreforge.blocks.machines.ui.IOMachineUI;
import com.olaz.coreforge.blocks.machines.ui.MachineUI;
import com.olaz.coreforge.data.Resource;

import java.util.HashMap;
import java.util.Map;

public class MachineView extends Table {
    private final DragAndDrop dragAndDrop;
    private final Map<Machine, MachineUI> machineUIMap = new HashMap<>();

    public MachineView(DragAndDrop dragAndDrop) {
        this.dragAndDrop = dragAndDrop;
    }

    public void setMachine(Machine machine) {
        clearChildren();

        if (machine instanceof IOMachine) {
            IOMachine io = (IOMachine) machine;

            this.add(
                machineUIMap.computeIfAbsent(machine,
                    m -> {
                    IOMachineUI ui = new IOMachineUI(io.getInputSlotSize(), io.getOutputSlotSize());
                    io.onInputChanged.connect(ui::updateInputSlot);
                    io.onOutputChanged.connect(ui::updateOutputSlot);

                    return ui;
                    })
            );

            setupDragAndDrop((IOMachine) machine);
        }
    }

    private void setupDragAndDrop(IOMachine machine) {
        IOMachineUI ui = (IOMachineUI) machineUIMap.get(machine);

        for (int i = 0; i < ui.getInputSlots().size; i++) {
            int slotIndex = i;
            dragAndDrop.addTarget(new DragAndDrop.Target(ui.getInputSlots().get(slotIndex)) {
                @Override
                public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    return true;
                }

                @Override
                public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    if (payload.getObject() instanceof Resource) {
                        machine.addResource(
                            slotIndex,
                            new MachineResourceEntry((Resource) payload.getObject(), 1));
                    }
                }
            });
        }
    }

    public void removeMachineFromUIMap(Machine machine) {
        machineUIMap.remove(machine);
    }

}
