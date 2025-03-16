package life.thoms.wandering_traders.message.command;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public enum LostLootCommandMessage {
    LOST_LOOT_TITLE("command.lostloot.view.title"),
    NO_LOOT("command.lostloot.view.no_loot"),
    NO_LOOT_OTHER("command.lostloot.view.no_loot_other"),
    LOOT_CLEARED("command.lostloot.clear.loot_cleared");

    private final String key;

    LostLootCommandMessage(String key) {
        this.key = key;
    }

    public MutableComponent getMessage() {
        return Component.translatable(key);
    }

    public MutableComponent getMessage(String append) {
        return Component.translatable(key).append(append);
    }
}
