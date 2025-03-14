package life.thoms.wandering_traders.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import life.thoms.wandering_traders.server.data.LostLootData;
import net.minecraft.SharedConstants;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.Bootstrap;
import net.minecraft.world.item.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ServerDataUtilTest {

    private static final UUID playerUUID = UUID.randomUUID();
    private static final UUID player2UUID = UUID.randomUUID();
    private static List<ItemStack> playerLoot;
    private static List<ItemStack> player2Loot;

    @BeforeAll
    public static void beforeAll() {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();
    }

    @Test
    void saveNbtTest() {
        // Save to result of saveNbt to compound
        CompoundTag rootCompound = prepareSaveOrLoadNbt();
        assertTrue(rootCompound.contains("wandering-traders-player-loot"));
        CompoundTag compound = rootCompound.getCompound("wandering-traders-player-loot");

        // Test if was saved and loads correctly
        assertTrue(compound.contains(playerUUID.toString()));
        CompoundTag randomUUIDCompound = compound.getCompound(playerUUID.toString());
        assertEquals(2, randomUUIDCompound.getAllKeys().size());

        for (String randomUUIDStr : randomUUIDCompound.getAllKeys()) {
            assertNotNull(randomUUIDStr);
            CompoundTag playerLootCompound = randomUUIDCompound.getCompound(randomUUIDStr);
            assertNotNull(playerLootCompound);
            String encodedStack = playerLootCompound.getAllKeys().stream().toList().getFirst();
            assertNotNull(encodedStack);
            JsonElement encodedStackJson = JsonParser.parseString(encodedStack);
            assertNotNull(encodedStackJson);
            DataResult<ItemStack> result = ItemStack.CODEC.parse(JsonOps.INSTANCE, encodedStackJson);
            assertNotNull(result);
            Optional<ItemStack> optionalStack = result.result();
            assertTrue(optionalStack.isPresent());
            ItemStack stack = optionalStack.get();
            if (stack.getItem() instanceof ArmorItem) {
                assertEquals("[Test Item Name]", stack.getDisplayName().getString());
                assertSame(Items.NETHERITE_HELMET, stack.getItem());
            } else if (stack.getItem() instanceof BlockItem) {
                assertSame(Items.DIAMOND_BLOCK, stack.getItem());
                assertSame(10, stack.getCount());
            } else {
                assertSame(Items.DIAMOND_AXE, stack.getItem());
            }
        }
    }

    @Test
    void LoadNbtTest() {
        CompoundTag compound = prepareSaveOrLoadNbt();

        LostLootData.PLAYER_LOST_LOOT.clear();
        LostLootData.load(compound, null);

        assertSame(2, LostLootData.PLAYER_LOST_LOOT.size());
        for (UUID playerUUID : LostLootData.PLAYER_LOST_LOOT.keySet()) {
            assertTrue(LostLootData.PLAYER_LOST_LOOT.containsKey(playerUUID));
            List<ItemStack> playerStacks = LostLootUtil.getPlayerLoot(playerUUID);
            assertFalse(playerStacks.isEmpty());
            for (ItemStack stack : playerStacks) {
                if (stack.getItem() instanceof ArmorItem) {
                    assertEquals("[Test Item Name]", stack.getDisplayName().getString());
                    assertSame(Items.NETHERITE_HELMET, stack.getItem());
                } else if (stack.getItem() instanceof BlockItem) {
                    assertSame(Items.DIAMOND_BLOCK, stack.getItem());
                    assertSame(10, stack.getCount());
                } else {
                    assertSame(Items.DIAMOND_AXE, stack.getItem());
                }
            }
        }

    }

    private static CompoundTag prepareSaveOrLoadNbt() {
         playerLoot = List.of(
                new ItemStack(Items.DIAMOND_AXE),
                new ItemStack(Holder.direct(Items.NETHERITE_HELMET),
                        1,
                        DataComponentPatch.builder()
                                .set(DataComponents.CUSTOM_NAME, Component.literal("Test Item Name"))
                                .build())
        );

        player2Loot = List.of(
                new ItemStack(Items.DIAMOND_BLOCK, 10)
        );

        LostLootUtil.putPlayerLoot(playerUUID, playerLoot);
        LostLootUtil.putPlayerLoot(player2UUID, player2Loot);
        LostLootData mockedInstance = Mockito.spy(LostLootData.class);
        HolderLookup.Provider provider = Mockito.mock(HolderLookup.Provider.class);
        return mockedInstance.save(new CompoundTag(), provider);
    }

}