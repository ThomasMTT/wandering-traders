package life.thoms.wandering_traders.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public enum EndTraderMessage {
    NO_TRADES("end_trader_message.no_trades"),
    ONLY_TRADE_WITH("end_trader_message.only_trade_with"),
    LEAVE_DIM("end_trader_message.leave_dim"),
    LEAVE_DIM_LOST("end_trader_message.leave_dim_lost"),
    LEAVE_DIM_ANGRY("end_trader_message.leave_dim_angry"),
    LEAVE_DIM_TOO_MANY_ITEMS("end_trader_message.leave_dim_too_many_items"),
    SUMMON_WITH_BELL("end_trader_message.summon_with_bell"),
    OTHER_STILL_AROUND("end_trader_message.other_still_around");

    private final String key;

    EndTraderMessage(String key) {
        this.key = key;
    }

    public MutableComponent getMessage() {
        return Component.translatable(key);
    }

    public MutableComponent getMessage(String append) {
        return Component.translatable(key).append(append);
    }

}
