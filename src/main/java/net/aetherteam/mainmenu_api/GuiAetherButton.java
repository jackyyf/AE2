package net.aetherteam.mainmenu_api;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderEngine;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiAetherButton extends GuiButton
{
    public int scrollMax = 100;
    public int scrollHeight = this.scrollMax;
    public int scrollMin = 115;
    public int scrollCrop = 20;
    public int scrollCropMax = 90;
    public boolean retracting = false;

    public GuiAetherButton(int i, int j, int k, String s)
    {
        super(i, j, k, s);
    }

    public GuiAetherButton(int i, int j, int k, int l, int i1, String s)
    {
        super(i, j, k, l, i1, s);
        this.enabled = true;
        this.drawButton = true;
    }

    protected int getHoverState(boolean flag)
    {
        byte byte0 = 1;

        if (!this.enabled)
        {
            byte0 = 0;
        } else if (flag)
        {
            if (byte0 < 2)
            {
                byte0 = (byte) (byte0 + 1);
            }
            if (this.scrollCrop < this.scrollCropMax)
            {
                this.scrollCrop += 1;
            }
            if (this.scrollHeight < this.scrollMin) this.scrollHeight += 1;
        } else
        {
            if (this.scrollCrop > this.scrollCropMax)
            {
                this.scrollCrop -= 1;
            }
            if (this.scrollHeight > this.scrollMax) this.scrollHeight -= 1;
            if (this.scrollHeight == this.scrollMax)
            {
                this.retracting = false;
            }
        }
        return byte0;
    }

    public void drawButton(Minecraft minecraft, int i, int j)
    {
        if (!this.drawButton)
        {
            return;
        }

        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);

        FontRenderer fontrenderer = minecraft.fontRenderer;
        GL11.glBindTexture(3553, minecraft.renderEngine.getTexture("/net/aetherteam/mainmenu_api/icons/aether/buttons.png"));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        boolean flag = (i >= this.xPosition) && (j >= this.yPosition) && (i < this.xPosition + this.width) && (j < this.yPosition + this.height);
        int k = getHoverState(flag);
        drawTexturedModalRect(this.xPosition + this.scrollHeight - 90, this.yPosition, 0, 46 + k * 20, this.width / 2, this.height);
        drawTexturedModalRect(this.xPosition + this.scrollHeight + this.width / 2 - 90, this.yPosition, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
        mouseDragged(minecraft, i, j);

        GL11.glDisable(3042);

        minecraft.renderEngine.resetBoundTexture();

        if (!this.enabled)
        {
            drawString(fontrenderer, this.displayString, this.xPosition + this.width / 10 + this.scrollHeight - 80, this.yPosition + (this.height - 8) / 2, -6250336);
        } else if (flag)
        {
            drawString(fontrenderer, this.displayString, this.xPosition + this.width / 10 + this.scrollHeight - 80, this.yPosition + (this.height - 8) / 2, 7851212);
        } else
            drawString(fontrenderer, this.displayString, this.xPosition + this.width / 10 + this.scrollHeight - 80, this.yPosition + (this.height - 8) / 2, 14737632);
    }

    protected void mouseDragged(Minecraft var1, int var2, int var3)
    {
    }

    public void mouseReleased(int var1, int var2)
    {
    }

    public boolean mousePressed(Minecraft var1, int var2, int var3)
    {
        return (this.enabled) && (var2 >= this.xPosition) && (var3 >= this.yPosition) && (var2 < this.xPosition + this.width) && (var3 < this.yPosition + this.height);
    }

}

/* Location:           D:\Dev\Mc\forge_orl\mcp\jars\bin\aether.jar
 * Qualified Name:     net.aetherteam.mainmenu_api.GuiAetherButton
 * JD-Core Version:    0.6.2
 */