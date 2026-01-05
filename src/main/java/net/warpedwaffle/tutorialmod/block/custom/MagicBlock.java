package net.warpedwaffle.tutorialmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.warpedwaffle.tutorialmod.item.ModItems;

import java.util.List;

public class MagicBlock extends Block {
    private final float pitchRange;

    public MagicBlock(float pitchRange, Settings settings) {
        super(settings);
        this.pitchRange = pitchRange;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
            world.playSound(null, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 2.0F, world.getRandom().nextFloat() * this.pitchRange + (this.pitchRange > 2.0F ? (1F-0.5F*this.pitchRange) : 0));
            return ActionResult.success(world.isClient);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof ItemEntity itemEntity) {
            if (itemEntity.getStack().getItem() == ModItems.RAW_PINK_GARNET) {
                itemEntity.setStack(new ItemStack(Items.DIAMOND, itemEntity.getStack().getCount()));
                itemEntity.setToDefaultPickupDelay();
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("tooltip.tutorialmod.magic_block.tooltip"));
        super.appendTooltip(stack, context, tooltip, options);
    }
}
