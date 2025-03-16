package life.thoms.wandering_traders.command;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public enum CommandMessage {
    CANNOT_RUN_FROM_CONSOLE("command.generic.cannot_run_from_console");

    private final String key;

    CommandMessage(String key) {
        this.key = key;
    }

    public MutableComponent getMessage() {
        return Component.translatable(key);
    }

    public MutableComponent getMessage(String append) {
        return Component.translatable(key).append(append);
    }
}
