package net.warpedwaffle.tutorialmod.util;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.warpedwaffle.tutorialmod.TutorialMod;
import net.warpedwaffle.tutorialmod.component.ModDataComponentTypes;
import net.warpedwaffle.tutorialmod.item.ModItems;

public class ModModelPredicates {
    public static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(ModItems.CHISEL, Identifier.of(TutorialMod.MOD_ID, "used"),
                (stack, world, entity, seed) -> stack.get(ModDataComponentTypes.COORDINATES) != null ? 1f : 0f);


    }
}
