package com.gildedrose;

import static com.gildedrose.ItemNames.*;

public class ItemDecoratorFactory {

    public ItemDecorator getItemDecorator(Item item){
        if (item.name.equals(BRIE)) {
            return new Brie(item);
        }
        else if (item.name.equals(BACKSTAGE_PASSES_ITEM)){
            return new Ticket(item);
        }
        else if (item.name.equals(CONJURED_ITEM)){
            return new ConjuredItem(item);
        }
        else if (item.name.equals(SULFURAS)){
            return new Sulfuras(item);
        }
        else {
            return new ItemDecorator(item);
        }
    }
}
