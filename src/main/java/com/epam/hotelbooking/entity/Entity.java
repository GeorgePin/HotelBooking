package com.epam.hotelbooking.entity;

import java.io.Serializable;

/**
 * Abstract entity class which implements {@code Identifable} and
 * {@code Serializable}
 * 
 * @author George Papkevich
 * @version 1.0
 * @since 1.0
 */
public abstract class Entity implements Identifable, Serializable {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3038839248040482638L;

}
