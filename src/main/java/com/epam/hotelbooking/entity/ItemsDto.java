package com.epam.hotelbooking.entity;

import java.util.List;

public class ItemsDto<T extends Entity> extends Entity {

    private static final long serialVersionUID = 8073494403894810162L;
    private Long id;
    private List<T> items;
    private Integer amountOfPages;

    public ItemsDto(List<T> items, Integer amountOfPages) {
        this.items = items;
        this.amountOfPages = amountOfPages;
    }

    @Override
    public Long getId() {
        return id;
    }

    public List<T> getItems() {
        return items;
    }

    public Integer getAmountOfPages() {
        return amountOfPages;
    }

}
