package com.epam.hotelbooking.entity;

import java.util.List;

public class ItemsDto extends Entity {
    private static final long serialVersionUID = 8073494403894810162L;
    private Long id;
    private List<? extends Entity> items;
    private Integer amountOfPages;

    public ItemsDto(List<? extends Entity> items, Integer amountOfPages) {
        this.items = items;
        this.amountOfPages = amountOfPages;
    }

    @Override
    public Long getId() {
        return id;
    }

    public List<? extends Entity> getItems() {
        return items;
    }

    public Integer getAmountOfPages() {
        return amountOfPages;
    }

}
