package net.aetherteam.aether.notifications.description;

import net.aetherteam.aether.notifications.Notification;
import net.aetherteam.aether.party.Party;
import net.aetherteam.aether.party.PartyController;
import net.aetherteam.aether.party.members.PartyMember;

public class DungeonRequestContents extends NotificationContents
{
    public String getTitle(Notification notification)
    {
        return "§r§n" + notification.getTypeName() + "§r - " + new StringBuilder().append("§r§o来自于 ").append(notification.getSenderName()).toString();
    }

    public String getDescription(Notification notification)
    {
        PartyMember recruiter = PartyController.instance().getMember(notification.getSenderName());
        Party party = PartyController.instance().getParty(recruiter);

        String description = null;

        if (party != null)
        {
            description = "你想和我们一起入侵地牢吗?" + " 我的公会是 " + new StringBuilder().append('"').append(party.getName()).append('"').toString() + " 警告: 地牢是一个非常危险的地方, 注意贵重物品的丢失";
        } else
        {
            description = "很抱歉, 我已退出公会, 你可以拒绝此请求";
        }

        return description;
    }
}

/* Location:           D:\Dev\Mc\forge_orl\mcp\jars\bin\aether.jar
 * Qualified Name:     net.aetherteam.aether.notifications.description.DungeonRequestContents
 * JD-Core Version:    0.6.2
 */