package com.olaz.coreforge.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.olaz.coreforge.blocks.machines.MachineInput;
import com.olaz.coreforge.blocks.machines.MachineOutput;
import com.olaz.coreforge.blocks.machines.MachineResourceEntry;
import com.olaz.coreforge.data.Size;
import com.olaz.coreforge.utils.observer.Event;

public abstract class IOMachine extends Machine implements MachineInput, MachineOutput {
    public Event<IOMachineEvent.InputAdd> onInputAdd = new Event<>();
    public Event<IOMachineEvent.InputRemove> onInputRemove = new Event<>();
    public Event<IOMachineEvent.OutputCreated> onOutputCreated = new Event<>();
    public Event<IOMachineEvent.OutputRemove> onOutputRemove = new Event<>();
    protected Array<MachineResourceEntry> inputs = new Array<>();
    protected Array<MachineResourceEntry> outputs = new Array<>();

    public IOMachine(String id, String description, Texture texture, Size size) {
        super(id, description, texture, size);
        setInOutSlotSize();
    }

    public IOMachine(String id, String description, Texture texture) {
        super(id, description, texture);
        setInOutSlotSize();
    }

    private void setInOutSlotSize() {
        inputs.size = getInputSlotSize();
        outputs.size = getOutputSlotSize();
    }


    @Override
    public void addResource(int slotIndex, MachineResourceEntry resourceEntry) {
        this.inputs.set(slotIndex, resourceEntry);

        if (resourceEntry != null) {
            onInputAdd.emit(new IOMachineEvent.InputAdd(slotIndex, resourceEntry));
            Gdx.app.log("IOMachine", "Input added: " + resourceEntry);
        } else {
            onInputRemove.emit(new IOMachineEvent.InputRemove(slotIndex));
            Gdx.app.log("IOMachine", "Input removed from slot " + slotIndex);
        }
    }

    @Override
    public void addOutput(int slotIndex, MachineResourceEntry resourceEntry) {
        this.outputs.set(slotIndex, resourceEntry);

        if (resourceEntry != null) {
            onOutputCreated.emit(new IOMachineEvent.OutputCreated(slotIndex, resourceEntry));
            Gdx.app.log("IOMachine", "Output created: " + resourceEntry);
        } else {
            onOutputRemove.emit(new IOMachineEvent.OutputRemove(slotIndex));
            Gdx.app.log("IOMachine", "Output removed from slot " + slotIndex);
        }
    }

    @Override
    public MachineResourceEntry getOutputResourceEntry(int slotIndex) {
        return outputs.get(slotIndex);
    }

    @Override
    public MachineResourceEntry getInputResourceEntry(int slotIndex) {
        return inputs.get(slotIndex);
    }
}
