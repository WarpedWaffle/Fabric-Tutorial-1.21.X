package net.warpedwaffle.tutorialmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.warpedwaffle.tutorialmod.TutorialMod;
import net.warpedwaffle.tutorialmod.block.custom.MagicBlock;

import java.util.List;

public class ModBlocks {
    public static final Block PINK_GARNET_BLOCK = registerBlock("pink_garnet_block",
            new Block(AbstractBlock.Settings.create().strength(4f).requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block RAW_PINK_GARNET_BLOCK = registerBlock("raw_pink_garnet_block",
            new Block(AbstractBlock.Settings.create().strength(3f).requiresTool()));
    public static final Block PINK_GARNET_ORE = registerBlock("pink_garnet_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.create().strength(3f).requiresTool()));
    public static final Block DEEPSLATE_PINK_GARNET_ORE = registerBlock("deepslate_pink_garnet_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 6), AbstractBlock.Settings.create().strength(4f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE)));

    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            new MagicBlock(2F, AbstractBlock.Settings.create().strength(7f)));

    public static final Block DARK_MAGIC_BLOCK = registerBlock("dark_magic_block",
            new MagicBlock(2f, AbstractBlock.Settings.create().strength(7f)) {
        @Override
        public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
            super.onSteppedOn(world, pos, state, entity);
            if (entity instanceof LivingEntity) {
                world.playSound(null, pos, SoundEvents.ENTITY_ALLAY_AMBIENT_WITHOUT_ITEM, SoundCategory.BLOCKS, 0.1F, world.getRandom().nextFloat() * 2.0F);
            }
        }
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
            tooltip.add(Text.translatable("tooltip.tutorialmod.dark_magic_block.tooltip.1"));
            tooltip.add(Text.translatable("tooltip.tutorialmod.dark_magic_block.tooltip.2"));
            tooltip.add(Text.translatable("tooltip.tutorialmod.dark_magic_block.tooltip.3"));
        }
            });

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        TutorialMod.LOGGER.info("Registering Mod Blocks for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModBlocks.PINK_GARNET_BLOCK);
            fabricItemGroupEntries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);
            fabricItemGroupEntries.add(ModBlocks.PINK_GARNET_ORE);
            fabricItemGroupEntries.add(ModBlocks.DEEPSLATE_PINK_GARNET_ORE);
        });
    }
}
