package net.aetherteam.aether.interfaces;

import net.aetherteam.aether.PlayerBaseAetherServer;
import net.aetherteam.aether.client.PlayerBaseAetherClient;
import net.minecraft.entity.player.EntityPlayer;

public abstract interface IAetherAccessory
{
    public static final int[] damageReduceAmountArray = {3, 8, 6, 3, 0, 1, 0, 0, 0, 0, 2, 0};

    public static final int[] maxDamageArray = {11, 16, 15, 13, 10, 10, 8, 10, 10, 10, 10, 10};

    public abstract boolean isTypeValid(int paramInt);

    public abstract boolean damageType();

    public abstract boolean damageType(int paramInt);

    public abstract void activatePassive(EntityPlayer paramEntityPlayer);

    public abstract void activateServerPassive(EntityPlayer paramEntityPlayer, PlayerBaseAetherServer paramPlayerBaseAetherServer);

    public abstract void activateClientPassive(EntityPlayer paramEntityPlayer, PlayerBaseAetherClient paramPlayerBaseAetherClient);
}

/* Location:           D:\Dev\Mc\forge_orl\mcp\jars\bin\aether.jar
 * Qualified Name:     net.aetherteam.aether.interfaces.IAetherAccessory
 * JD-Core Version:    0.6.2
 */