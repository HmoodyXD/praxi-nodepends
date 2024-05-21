package lol.vifez.praxi.commands.event.admin;

import lol.vifez.praxi.event.Event;
import lol.vifez.praxi.event.game.map.EventGameMap;
import lol.vifez.praxi.util.CC;
import lol.vifez.praxi.util.command.command.CPL;
import lol.vifez.praxi.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "event remove map", permission = "praxi.admin.event")
public class EventRemoveMapCommand {

	public void execute(Player player, @CPL("event") Event event, @CPL("map") EventGameMap gameMap) {
		if (event == null) {
			player.sendMessage(CC.RED + "An event type by that name does not exist.");
			player.sendMessage(CC.RED + "Types: sumo, corners");
			return;
		}

		if (gameMap == null) {
			player.sendMessage(CC.RED + "A map with that name does not exist.");
			return;
		}

		if (event.getAllowedMaps().remove(gameMap.getMapName())) {
			event.save();

			player.sendMessage(CC.GREEN + "You successfully removed the \"" + gameMap.getMapName() +
			                   "\" map from the \"" + event.getDisplayName() + "\" event.");
		}
	}

}
