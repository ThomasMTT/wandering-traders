package life.thoms.wandering_traders.menu;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ViewOnlySimpleContainer extends SimpleContainer {

    public ViewOnlySimpleContainer(int size) {
        super(size);
    }

    public ViewOnlySimpleContainer(ItemStack... items) {
        super(items);
    }

    @Override
    public @NotNull ItemStack addItem(ItemStack stack) {
        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            ItemStack itemStack = stack.copy();
            moveItemToEmptySlots(itemStack);
            return itemStack.isEmpty() ? ItemStack.EMPTY : itemStack;
        }
    }

    private void moveItemToEmptySlots(ItemStack stack) {
        for (int i = 0; i < this.getContainerSize(); i++) {
            ItemStack itemStack = this.getItem(i);
            if (itemStack.isEmpty()) {
                this.setItem(i, stack.copyAndClear());
                return;
            }
        }
    }

    @Override
    public boolean canAddItem(ItemStack stack) {
        boolean canAddItem = false;

        for (int i = 0; i < super.getContainerSize(); i++) { // TODO check
            ItemStack itemStack =  super.getItem(i);
            if (itemStack.isEmpty() || ItemStack.isSameItemSameTags(itemStack, stack) && itemStack.getCount() < 16) {
                canAddItem = true;
                break;
            }
        }

        return canAddItem;
    }

}