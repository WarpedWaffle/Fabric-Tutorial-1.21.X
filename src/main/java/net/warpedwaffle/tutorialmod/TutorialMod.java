package net.warpedwaffle.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.warpedwaffle.tutorialmod.block.ModBlocks;
import net.warpedwaffle.tutorialmod.component.ModDataComponentTypes;
import net.warpedwaffle.tutorialmod.item.ModItemGroups;
import net.warpedwaffle.tutorialmod.item.ModItems;
import net.warpedwaffle.tutorialmod.sound.ModSounds;
import net.warpedwaffle.tutorialmod.util.HammerUsageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItemGroups.registerItemGroups();

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        ModDataComponentTypes.registerDataComponentTypes();
        ModSounds.registerSounds();

        FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600);

        PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!world.isClient() && entity instanceof LivingEntity livingEntity) {
                if (livingEntity.getType().isIn(EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS)) {
                    player.sendMessage(Text.literal("very evasive insect"));
                    return ActionResult.FAIL;
                }
            }

            return ActionResult.PASS;
        });
	}
}