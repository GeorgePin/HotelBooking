package com.epam.hotelbooking.entity;

/**
 * Interface which is responsible for entity creation.
 * 
 * @author George Papkevich
 * @version 1.0
 * @since 1.0
 */
public interface Builder<T extends Entity> {

    /**
     * This method builds an entity with the previously entered parameters.
     * 
     * @return {@code Entity} Builded entity.
     * 
     */
    T build();

    /**
     * This method resets all variables in Builder.
     */
    void reset();

}
