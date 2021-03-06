package net.aetherteam.aether.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.inventory.Container;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public abstract class AetherInventoryEffectRenderer extends GuiContainerAether
{
    private boolean field_74222_o;

    public AetherInventoryEffectRenderer(Container par1Container)
    {
        super(par1Container);
    }

    public void initGui()
    {
        super.initGui();

        if (!this.mc.thePlayer.getActivePotionEffects().isEmpty())
        {
            this.guiLeft = (160 + (this.width - this.xSize - 200) / 2);
            this.field_74222_o = true;
        }
    }

    public void drawScreen(int par1, int par2, float par3)
    {
        super.drawScreen(par1, par2, par3);

        if (this.field_74222_o)
        {
            displayDebuffEffects();
        }
    }

    private void displayDebuffEffects()
    {
        int var1 = this.guiLeft - 124;
        int var2 = this.guiTop;
        Collection var4 = this.mc.thePlayer.getActivePotionEffects();

        if (!var4.isEmpty())
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(2896);
            int var6 = 33;

            if (var4.size() > 5)
            {
                var6 = 132 / (var4.size() - 1);
            }

            for (Iterator var7 = this.mc.thePlayer.getActivePotionEffects().iterator(); var7.hasNext(); var2 += var6)
            {
                PotionEffect var8 = (PotionEffect) var7.next();
                Potion var9 = Potion.potionTypes[var8.getPotionID()];
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.mc.renderEngine.bindTexture("/gui/inventory.png");
                drawTexturedModalRect(var1, var2, 0, 166, 140, 32);

                if (var9.hasStatusIcon())
                {
                    int var10 = var9.getStatusIconIndex();
                    drawTexturedModalRect(var1 + 6, var2 + 7, 0 + var10 % 8 * 18, 198 + var10 / 8 * 18, 18, 18);
                }

                String var12 = StatCollector.translateToLocal(var9.getName());

                if (var8.getAmplifier() == 1)
                {
                    var12 = var12 + " II";
                } else if (var8.getAmplifier() == 2)
                {
                    var12 = var12 + " III";
                } else if (var8.getAmplifier() == 3)
                {
                    var12 = var12 + " IV";
                }

                this.fontRenderer.drawStringWithShadow(var12, var1 + 10 + 18, var2 + 6, 16777215);
                String var11 = Potion.getDurationString(var8);
                this.fontRenderer.drawStringWithShadow(var11, var1 + 10 + 18, var2 + 6 + 10, 8355711);
            }
        }
    }
}

/* Location:           D:\Dev\Mc\forge_orl\mcp\jars\bin\aether.jar
 * Qualified Name:     net.aetherteam.aether.client.gui.AetherInventoryEffectRenderer
 * JD-Core Version:    0.6.2
 */