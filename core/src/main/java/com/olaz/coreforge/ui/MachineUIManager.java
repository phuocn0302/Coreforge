package com.olaz.coreforge.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.olaz.coreforge.blocks.IOMachine;
import com.olaz.coreforge.blocks.Machine;
import com.olaz.coreforge.blocks.machines.MachineResourceEntry;
import com.olaz.coreforge.blocks.machines.ui.IOMachineUI;
import com.olaz.coreforge.blocks.machines.ui.MachineUI;
import com.olaz.coreforge.blocks.machines.ui.components.InputSlot;
import com.olaz.coreforge.data.Resource;
import com.olaz.coreforge.systems.MainInventory;

import java.util.HashMap;
import java.util.Map;

public class MachineUIManager {
    private final DragAndDrop dragAndDrop;
    private final MainInventory mainInventory;
    private final Map<Machine, MachineUI> machineUIMap = new HashMap<>();

    public MachineUIManager(DragAndDrop dragAndDrop, MainInventory mainInventory) {
        this.dragAndDrop = dragAndDrop;
        this.mainInventory = mainInventory;
    }

    public MachineUI getMachineUI(Machine machine) {
        if (machine instanceof IOMachine) {
            return createIOMachineUI((IOMachine) machine);
        }
        return null;
    }

    private MachineUI createIOMachineUI(IOMachine io) {
        return machineUIMap.computeIfAbsent(io, machine -> {
            IOMachineUI ui = new IOMachineUI(io.getInputSlotSize(), io.getOutputSlotSize());
            io.onInputAdd.connect(ui::updateInputSlot);
            io.onInputRemove.connect(ui::updateInputSlot);
            io.onOutputCreated.connect(ui::updateOutputSlot);
            io.onOutputRemove.connect(ui::updateOutputSlot);
            setupDragAndDrop(io, ui);
            return ui;
        });
    }

    private void setupDragAndDrop(IOMachine machine, IOMachineUI ui) {
        for (int i = 0; i < ui.getInputSlots().size; i++) {
            int slotIndex = i;
            InputSlot inputSlot = ui.getInputSlots().get(slotIndex);

            // Inventory to input slot
            dragAndDrop.addTarget(new DragAndDrop.Target(inputSlot) {
                @Override
                public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    return true;
                }

                @Override
                public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    if (payload.getObject() instanceof Resource) {
                        Resource resource = (Resource) payload.getObject();

                        boolean isFromInventory = !(source.getActor() instanceof InputSlot);

                        if (isFromInventory) {
                            // Take from inventory and add to machine
                            if (mainInventory.getInventory().containsKey(resource) &&
                                mainInventory.getInventory().get(resource) > 0) {
                                machine.addResource(slotIndex, new MachineResourceEntry(resource, 1));
                                mainInventory.removeFromInventory(resource, 1);
                            }
                        } else {
                            // Move the resource between slot
                            machine.addResource(slotIndex, new MachineResourceEntry(resource, 1));
                        }
                    }
                }
            });

            // From input slot back to inventory
            dragAndDrop.addSource(new DragAndDrop.Source(inputSlot) {
                @Override
                public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                    MachineResourceEntry entry = machine.getInputResourceEntry(slotIndex);
                    if (entry != null && entry.getResource() != null && entry.getQuantity() > 0) {
                        DragAndDrop.Payload payload = new DragAndDrop.Payload();
                        payload.setObject(entry.getResource());

                        Image dragIcon = new Image(entry.getResource().getTexture());
                        payload.setDragActor(dragIcon);

                        return payload;
                    }
                    return null;
                }

                @Override
                public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                    if (payload != null && payload.getObject() instanceof Resource) {
                        Resource resource = (Resource) payload.getObject();

                        boolean isDroppedOnMachineSlot = target != null && target.getActor() instanceof InputSlot;

                        if (isDroppedOnMachineSlot) {
                            machine.addResource(slotIndex, null);
                        } else if (target == null || target.getActor() == null) {
                            machine.addResource(slotIndex, null);
                            mainInventory.addToInventory(resource, 1);
                        }
                    }
                }
            });
        }
    }

    public void removeMachine(Machine machine) {
        machineUIMap.remove(machine);
    }
}
