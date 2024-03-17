package dev.modusami.invmanagementsys.model;

import java.util.UUID;

/**
 * Represents a unique identifier for an inventory item.
 */
public class InventoryItemId {
    private final UUID id;

    /**
     * Constructs a new InventoryItemId with a randomly generated UUID.
     */
    public InventoryItemId() {
        this.id = UUID.randomUUID();
    }

    /**
     * Constructs a new InventoryItemId with the specified UUID.
     *
     * @param id the UUID to use as the unique identifier
     */
    public InventoryItemId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the UUID associated with this InventoryItemId.
     *
     * @return the UUID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns a string representation of this InventoryItemId.
     *
     * @return a string representation of the UUID
     */
    @Override
    public String toString() {
        return id.toString();
    }

    /**
     * Checks if this InventoryItemId is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InventoryItemId other = (InventoryItemId) obj;
        return id.equals(other.id);
    }

    /**
     * Returns a hash code value for this InventoryItemId.
     *
     * @return a hash code value
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}