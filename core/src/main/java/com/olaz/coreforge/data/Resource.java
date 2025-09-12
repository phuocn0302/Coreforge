package com.olaz.coreforge.data;

import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

public class Resource {
    private final String id;
    private final String name;
    private final String description;
    private final Texture texture;

    public Resource(String id, String name, String description, Texture texture) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Texture getTexture() {
        return texture;
    }

    @Override
    public String toString() {
        return "Resource{" +
            "name='" + name + '\'' +
            ", id='" + id + '\'' +
            ", description='" + description + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return Objects.equals(name, resource.name) && Objects.equals(id, resource.id) && Objects.equals(description, resource.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, description);
    }
}
