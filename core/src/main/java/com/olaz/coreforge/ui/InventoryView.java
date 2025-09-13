package com.olaz.coreforge.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.olaz.coreforge.data.Resource;
import com.olaz.coreforge.systems.MainInventory;

import java.util.Map;

public class InventoryView extends Table {
    private final MainInventory mainInventory;
    private final Table listTable;
    private final Skin skin;

    public InventoryView(MainInventory mainInventory, Skin skin) {
        this.mainInventory = mainInventory;
        this.skin = skin;

        listTable = new Table();
        ScrollPane scroll = new ScrollPane(listTable);
        add(scroll).expand().fill();
        refresh();
    }

    public void refresh() {
        listTable.clearChildren();
        for (Map.Entry<Resource, Integer> entry : mainInventory.getInventory().entrySet()) {
            Resource resource = entry.getKey();
            int qty = entry.getValue();
            listTable.add(new ResourceEntryView(resource, qty, skin)).padBottom(2).row();
        }
    }

    private static class ResourceEntryView extends Table {
        public ResourceEntryView(Resource resource, int quantity, Skin skin) {
            setBackground(new TextureRegionDrawable(new TextureRegion(
                new Texture("ui/inventory/resource_entry.png")
            )));

            Image icon = new Image(resource.getTexture());
            Label name = new Label(resource.getName(), skin);
            Label qty = new Label("x" + quantity, skin);

            name.setFontScale(0.6f);
            qty.setFontScale(0.5f);

            name.setEllipsis(true);

            Table info = new Table();
            info.align(Align.left);
            info.setClip(true);
            info.add(name).size(42, 10).left().padBottom(1);
            info.row();
            info.add(qty).size(42, 5).left();

            this.align(Align.left);
            this.add(icon).size(16, 16).pad(2);
            this.add(info).size(42, 18).padLeft(2);
        }
    }
}
