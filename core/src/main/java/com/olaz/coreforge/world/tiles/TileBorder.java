package com.olaz.coreforge.world.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileBorder {
    public TextureRegion top;
    public TextureRegion bottom;
    public TextureRegion left;
    public TextureRegion right;
    public TextureRegion topLeft;
    public TextureRegion topRight;
    public TextureRegion bottomLeft;
    public TextureRegion bottomRight;
    public TextureRegion innerTopLeft;
    public TextureRegion innerTopRight;
    public TextureRegion innerBottomLeft;
    public TextureRegion innerBottomRight;

    public TileBorder(Texture borderTilemap) {
        if (borderTilemap.getWidth() != 80 || borderTilemap.getHeight() != 48) {
            throw new IllegalArgumentException("Border tilemap must be 80x48 pixels");
        }

        TextureRegion[][] r = TextureRegion.split(borderTilemap, 16, 16);

        // First row (y=0)
        topLeft         = r[0][0];
        top             = r[0][1];
        topRight        = r[0][2];
        innerBottomRight= r[0][3];
        innerBottomLeft = r[0][4];

        // Second row (y=16)
        left            = r[1][0];
        right           = r[1][2];
        innerTopRight   = r[1][3];
        innerTopLeft    = r[1][4];

        // Third row (y=32)
        bottomLeft      = r[2][0];
        bottom          = r[2][1];
        bottomRight     = r[2][2];
    }

    public static class BorderMask {
        public static final int TOP = 1;
        public static final int BOTTOM = 1 << 1;
        public static final int LEFT = 1 << 2;
        public static final int RIGHT = 1 << 3;
        public static final int TOP_LEFT = 1 << 4;
        public static final int TOP_RIGHT = 1 << 5;
        public static final int BOTTOM_LEFT = 1 << 6;
        public static final int BOTTOM_RIGHT = 1 << 7;
        public static final int INNER_TOP_LEFT = 1 << 8;
        public static final int INNER_TOP_RIGHT = 1 << 9;
        public static final int INNER_BOTTOM_LEFT = 1 << 10;
        public static final int INNER_BOTTOM_RIGHT = 1 << 11;
    }
}
