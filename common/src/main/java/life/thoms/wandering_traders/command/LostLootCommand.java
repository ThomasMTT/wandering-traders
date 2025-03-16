package life.thoms.wandering_traders.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import life.thoms.wandering_traders.menu.ViewOnlyChestMenu;
import life.thoms.wandering_traders.menu.ViewOnlySimpleContainer;
import life.thoms.wandering_traders.util.LostLootUtil;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Objects;

public class LostLootCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context, Commands.CommandSelection commandSelection) {
        dispatcher.register(Commands.literal("lostloot")
                .then(Commands.literal("clear").executes(LostLootCommand::clearLoot)
                        .then(Commands.argument("Player", EntityArgument.player()).executes(LostLootCommand::clearOtherLoot))
                )
                .then(Commands.literal("view").executes(LostLootCommand::viewLoot)
                        .then(Commands.argument("Player", EntityArgument.player()).executes(LostLootCommand::viewOtherLoot))
                )
        );
    }

    private static int viewLoot(CommandContext<CommandSourceStack> context) {
        return viewPlayerLoot(context, null);
    }

    private static int viewOtherLoot(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer target = EntityArgument.getPlayer(context, "Player");
        return viewPlayerLoot(context, target);
    }

    private static int viewPlayerLoot(CommandContext<CommandSourceStack> context, ServerPlayer target) {
        ServerPlayer player = context.getSource().getPlayer();
        if (player != null) {
            List<ItemStack> lostLoot = LostLootUtil.getPlayerLoot(Objects.requireNonNullElse(target, player));

            if (lostLoot != null && !lostLoot.isEmpty()) {
                ViewOnlySimpleContainer lostLootContainer = new ViewOnlySimpleContainer(54);
                lostLoot.forEach(lostLootContainer::addItem);

                MutableComponent title;
                if (target == null) {
                    title = LostLootCommandMessage.LOST_LOOT_TITLE.getMessage();
                } else {
                    title = LostLootCommandMessage.LOST_LOOT_TITLE.getMessage()
                            .append(Component.literal(" (" + target.getName().getString() + ")"));
                }

                SimpleMenuProvider menuProvider = new SimpleMenuProvider((i, inventory, player1) ->
                        ViewOnlyChestMenu.sixRows(1, player1.getInventory(), lostLootContainer), title);
                player.openMenu(menuProvider);
                return 0;
            } else {
                if (target == null) {
                    context.getSource().sendSystemMessage(LostLootCommandMessage.NO_LOOT.getMessage());
                } else {
                    MutableComponent message = Component.literal(target.getName().getString() + " ");
                    message.append(LostLootCommandMessage.NO_LOOT_OTHER.getMessage());
                    context.getSource().sendSystemMessage(message);
                }
            }
        } else {
            context.getSource().sendSystemMessage(CommandMessage.CANNOT_RUN_FROM_CONSOLE.getMessage());
        }
        return 1;
    }

    private static int clearLoot(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        if (player != null) {
            LostLootUtil.clearPlayerLoot(player);
            context.getSource().sendSystemMessage(LostLootCommandMessage.LOOT_CLEARED.getMessage());
            return 0;
        }
        return 1;
    }

    private static int clearOtherLoot(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer target = EntityArgument.getPlayer(context, "Player");
        LostLootUtil.clearPlayerLoot(target);
        MutableComponent message = LostLootCommandMessage.LOOT_CLEARED.getMessage()
                .append(Component.literal(" (" + target.getName().getString() + ")"));
        context.getSource().sendSystemMessage(message);
        return 0;
    }
}
