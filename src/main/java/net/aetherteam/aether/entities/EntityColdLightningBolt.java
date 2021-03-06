package net.aetherteam.aether.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import net.aetherteam.aether.blocks.AetherBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityWeatherEffect;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityColdLightningBolt extends EntityWeatherEffect
{
    private int lightningState;
    public long boltVertex = 0L;
    private int boltLivingTime;
    DamageSource damageSource;

    public EntityColdLightningBolt(World var1, double var2, double var4, double var6)
    {
        super(var1);
        this.setLocationAndAngles(var2, var4, var6, 0.0F, 0.0F);
        this.lightningState = 2;
        this.boltVertex = this.rand.nextLong();
        this.boltLivingTime = this.rand.nextInt(3) + 1;

        if (!var1.isRemote && var1.difficultySetting >= 2 && var1.doChunksNearChunkExist(MathHelper.floor_double(var2), MathHelper.floor_double(var4), MathHelper.floor_double(var6), 10))
        {
            int var8 = MathHelper.floor_double(var2);
            int var9 = MathHelper.floor_double(var4);
            int var10 = MathHelper.floor_double(var6);

            if (var1.getBlockId(var8, var9, var10) == 0 && AetherBlocks.ColdFire.canPlaceBlockAt(var1, var8, var9, var10))
            {
                var1.setBlock(var8, var9, var10, AetherBlocks.ColdFire.blockID);
            }

            for (var8 = 0; var8 < 4; ++var8)
            {
                var9 = MathHelper.floor_double(var2) + this.rand.nextInt(3) - 1;
                var10 = MathHelper.floor_double(var4) + this.rand.nextInt(3) - 1;
                int var11 = MathHelper.floor_double(var6) + this.rand.nextInt(3) - 1;

                if (var1.getBlockId(var9, var10, var11) == 0 && AetherBlocks.ColdFire.canPlaceBlockAt(var1, var9, var10, var11))
                {
                    var1.setBlock(var9, var10, var11, AetherBlocks.ColdFire.blockID);
                }
            }
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (this.lightningState == 2)
        {
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "ambient.weather.thunder", 2.0F, 0.8F + this.rand.nextFloat() * 0.2F);
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
        }

        --this.lightningState;

        if (this.lightningState < 0)
        {
            if (this.boltLivingTime == 0)
            {
                this.setDead();
            } else if (this.lightningState < -this.rand.nextInt(10))
            {
                --this.boltLivingTime;
                this.lightningState = 1;
                this.boltVertex = this.rand.nextLong();

                if (!this.worldObj.isRemote && this.worldObj.doChunksNearChunkExist(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 10))
                {
                    int var1 = MathHelper.floor_double(this.posX);
                    int var2 = MathHelper.floor_double(this.posY);
                    int var3 = MathHelper.floor_double(this.posZ);

                    if (this.worldObj.getBlockId(var1, var2, var3) == 0 && AetherBlocks.ColdFire.canPlaceBlockAt(this.worldObj, var1, var2, var3))
                    {
                        this.worldObj.setBlock(var1, var2, var3, AetherBlocks.ColdFire.blockID);
                    }
                }
            }
        }

        if (this.lightningState >= 0)
        {
            if (this.worldObj.isRemote)
            {
                this.worldObj.lastLightningBolt = 2;
            } else
            {
                double var7 = 3.0D;
                List var6 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getAABBPool().getAABB(this.posX - var7, this.posY - var7, this.posZ - var7, this.posX + var7, this.posY + 6.0D + var7, this.posZ + var7));

                for (int var4 = 0; var4 < var6.size(); ++var4)
                {
                    Entity var5 = (Entity) var6.get(var4);
                    this.damageSource = (new CustomDamageSource("无福消受风暴怪冰冷的吻", var5, this)).setDeathMessage("无福消受风暴怪冰冷的吻");

                    if (!(var5 instanceof EntityTempest) && !(var5 instanceof EntityItem))
                    {
                        var5.attackEntityFrom(this.damageSource, 6);
                    }
                }
            }
        }
    }

    protected void entityInit() {}

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound var1)
    {}

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound var1)
    {}

    @SideOnly(Side.CLIENT)

    /**
     * Checks using a Vec3d to determine if this entity is within range of that vector to be rendered. Args: vec3D
     */
    public boolean isInRangeToRenderVec3D(Vec3 var1)
    {
        return this.lightningState >= 0;
    }
}
