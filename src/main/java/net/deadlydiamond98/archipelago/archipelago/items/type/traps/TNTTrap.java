package net.deadlydiamond98.archipelago.archipelago.items.type.traps;

import net.deadlydiamond98.archipelago.archipelago.items.type.AbstractAPItem;
import net.minecraft.block.Blocks;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.TntEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TNTTrap extends AbstractTrapItem {
    @Override
    public void applyReward(ServerPlayerEntity player) {
        World world = player.getWorld();
        Vec3d pos = player.getPos();

        if (Math.random() < 0.4) {
            // 40% chance: place TNT block
            BlockPos blockPos = player.getBlockPos();
            
            // Check if block already exists, find nearest air block
            if (!world.getBlockState(blockPos).isAir()) {
                blockPos = blockPos.up();
                while (!world.getBlockState(blockPos).isAir() && blockPos.getY() < world.getTopY()) {
                    blockPos = blockPos.up();
                }
            }
            
            world.setBlockState(blockPos, Blocks.TNT.getDefaultState().with(TntBlock.UNSTABLE, Math.random() > 0.1));
            world.playSound(
                null,
                blockPos.getX(),
                blockPos.getY(),
                blockPos.getZ(),
                SoundEvents.ENTITY_TNT_PRIMED,
                SoundCategory.BLOCKS,
                1, 1
            );
        } else {
            // 60% chance: spawn TNT entity
            TntEntity tntEntity = new TntEntity(world, pos.getX(), pos.getY(), pos.getZ(), null);
            tntEntity.setFuse(40);
            world.spawnEntity(tntEntity);
            world.playSound(
            null,
            tntEntity.getX(),
            tntEntity.getY(),
            tntEntity.getZ(),
            SoundEvents.ENTITY_TNT_PRIMED,
            SoundCategory.BLOCKS,
            1, 1
            );
        }
    }
}
