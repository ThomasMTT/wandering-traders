package life.thoms.wandering_traders.util.trader;

import life.thoms.wandering_traders.server.data.LostLootData;
import net.minecraft.SharedConstants;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.Bootstrap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EndTraderUtilTest {

    private static UUID playerUUID;
    private static MerchantOffers offers;

    @BeforeAll
    public static void beforeAll() {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();
    }

    private static void prepareCreateOffersFromLostLoot() {
        LostLootData.INSTANCE = Mockito.mock(LostLootData.class);
        playerUUID = UUID.randomUUID();
        offers = new MerchantOffers();
        offers.clear();
        List<ItemStack> playerLoot = List.of(
                new ItemStack(Items.DIAMOND_AXE),
                new ItemStack(Holder.direct(Items.NETHERITE_HELMET),
                        1,
                        DataComponentPatch.builder()
                                .set(DataComponents.CUSTOM_NAME, Component.literal("Test Item Name"))
                                .build())
        );
        LostLootData.PLAYER_LOST_LOOT.put(playerUUID, playerLoot);
    }

    @Test
    void createOffer_IsValidOfferTest() {
        ItemStack stack = new ItemStack(Items.NETHERITE_LEGGINGS);
        MerchantOffer offer = EndTraderUtil.createOffer(stack);
        assertNotNull(offer.getItemCostA());
        assertNotNull(offer.getResult());
        assertEquals(0, offer.getUses());
        assertFalse(offer.isOutOfStock());
        offer.increaseUses();
        assertTrue(offer.isOutOfStock());
    }

    @Test
    void createOffer_IsAllowedPriceItemTest() {
        ItemStack stack = new ItemStack(Items.NETHERITE_LEGGINGS);
        MerchantOffer offer = EndTraderUtil.createOffer(stack);

        assertTrue(EndTraderUtil.PRICE_ITEMS.contains(offer.getItemCostA().itemStack().getItem()));
        if (offer.getItemCostB().isPresent() && offer.getItemCostB().get().itemStack().getItem() != Items.AIR) {
            assertTrue(EndTraderUtil.PRICE_ITEMS.contains(offer.getItemCostB().get().itemStack().getItem()));
        }
    }

    @Test
    void createOffersFromLostLoot_OffersCreated() {
        prepareCreateOffersFromLostLoot();
        MerchantOffers newOffers = EndTraderUtil.createOffersFromLostLoot(playerUUID, offers);
        assertSame(2, newOffers.size());
    }

    @Test
    void createOffersFromLostLoot_PlayerLootListEmptiedTest() {
        prepareCreateOffersFromLostLoot();
        EndTraderUtil.createOffersFromLostLoot(playerUUID, offers);

        assertTrue(LostLootData.PLAYER_LOST_LOOT.get(playerUUID).isEmpty());
    }

}