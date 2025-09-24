package com.olaz.coreforge.blocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.olaz.coreforge.blocks.machines.MachineInput;
import com.olaz.coreforge.blocks.machines.MachineOutput;
import com.olaz.coreforge.blocks.machines.MachineResourceEntry;
import com.olaz.coreforge.data.Size;
import com.olaz.coreforge.utils.observer.Event;

public abstract class IOMachine extends Machine implements MachineInput, MachineOutput {
    public Event<IOMachineEvent.InputChanged> onInputChanged = new Event<>();
    public Event<IOMachineEvent.OutputChanged> onOutputChanged = new Event<>();
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

        onInputChanged.emit(new IOMachineEvent.InputChanged(slotIndex, resourceEntry));
        Gdx.app.log("IOMachine", resourceEntry.toString());
    }

    @Override
    public void addOutput(int slotIndex, MachineResourceEntry resourceEntry) {
        this.outputs.set(slotIndex, resourceEntry);

        onOutputChanged.emit(new IOMachineEvent.OutputChanged(slotIndex, resourceEntry));
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
