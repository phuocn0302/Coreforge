package com.olaz.coreforge.blocks.machines.ui.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.olaz.coreforge.blocks.machines.MachineResourceEntry;

public class Slot extends Stack {
    private static final Texture slotTexture = new Texture(Gdx.files.internal("ui/machine/resourceSlot.png"));
    private final Image background = new Image(slotTexture);
    private final Image resourceImage = new Image();

    private MachineResourceEntry resourceEntry;

    public Slot() {
        add(background);
        add(resourceImage);
    }

    public void setResource(MachineResourceEntry entry) {
        this.resourceEntry = entry;
        if (entry != null && entry.getResource() != null) {
            Texture tex = entry.getResource().getTexture();
            resourceImage.setDrawable(new TextureRegionDrawable(new TextureRegion(tex)));
            resourceImage.setVisible(true);
        } else {
            resourceImage.setDrawable(null);
            resourceImage.setVisible(false);
        }
    }

    public void clearResource() {
        setResource(null);
    }

    public MachineResourceEntry getResourceEntry() {
        return resourceEntry;
    }

    public void setResourceTexture(Texture tex) {
        resourceImage.setDrawable(new TextureRegionDrawable(new TextureRegion(tex)));
        resourceImage.setVisible(true);
    }

    public void clearResourceTexture() {
        resourceImage.setDrawable(null);
        resourceImage.setVisible(false);
    }
}
