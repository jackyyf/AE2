package net.aetherteam.aether.creative_tabs;

import net.aetherteam.aether.items.AetherItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class AetherToolsTab extends CreativeTabs
{
    public AetherToolsTab()
    {
        super("AetherToolsTab");
    }

    public ItemStack getIconItemStack()
    {
        return new ItemStack(AetherItems.GravititePickaxe);
    }

    public String getTabLabel()
    {
        return "以太II|工具";
    }

    public String getTranslatedTabLabel()
    {
        return getTabLabel();
    }
}

/* Location:           D:\Dev\Mc\forge_orl\mcp\jars\bin\aether.jar
 * Qualified Name:     net.aetherteam.aether.creative_tabs.AetherToolsTab
 * JD-Core Version:    0.6.2
 */