package us.talabrek.ultimateskyblock.chat;

import us.talabrek.ultimateskyblock.uSkyBlock;
import us.talabrek.ultimateskyblock.util.po.I18nUtil;

/**
 * Talk to your party
 */
public class PartyTalkCommand extends IslandChatCommand {

    public PartyTalkCommand(uSkyBlock plugin, ChatLogic chatLogic) {
        super(plugin, chatLogic, "partytalk|ptalk|ptk", "usb.party.talk", I18nUtil.tr("talk to your island party"));
    }
}
