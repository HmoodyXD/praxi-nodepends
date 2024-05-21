package lol.vifez.praxi.event.game.menu;

import lol.vifez.praxi.event.Event;
import lol.vifez.praxi.util.CC;
import lol.vifez.praxi.util.ItemBuilder;
import lol.vifez.praxi.util.TextSplitter;
import lol.vifez.praxi.util.menu.Button;
import lol.vifez.praxi.util.menu.Menu;
import lol.vifez.praxi.util.menu.button.DisplayButton;
import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHostMenu extends Menu {

	{
		setPlaceholder(true);
	}

	@Override
	public String getTitle(Player player) {
		return "&6&lSelect an Event";
	}

	@Override
	public Map<Integer, Button> getButtons(Player player) {
		Map<Integer, Button> buttons = new HashMap<>();

		int pos = 10;

		for (Event event : Event.events) {
			buttons.put(pos++, new SelectEventButton(event));
		}

		if (pos <= 16) {
			for (int i = pos; i < 16; i++) {
				buttons.put(i, new DisplayButton(new ItemBuilder(Material.STAINED_GLASS_PANE)
						.durability(7).name(" ").build(), true));
			}
		}

		return buttons;
	}

	@AllArgsConstructor
	private class SelectEventButton extends Button {

		private Event event;

		@Override
		public ItemStack getButtonItem(Player player) {
			List<String> lore = new ArrayList<>();
			lore.add(CC.MENU_BAR);

			for (String descriptionLine : TextSplitter.split(28, event.getDescription(), "&7", " ")) {
				lore.add(" " + descriptionLine);
			}

			lore.add("");

			if (event.canHost(player)) {
				lore.add(ChatColor.GREEN + "You can host this event.");
				lore.add(ChatColor.GREEN + "Maximum Slots: " + ChatColor.YELLOW + 50);
			} else {
				lore.add(ChatColor.RED + "You cannot host this event.");
				lore.add(ChatColor.RED + "Purchase a rank upgrade on our store.");
			}

			lore.add(CC.MENU_BAR);

			return new ItemBuilder(event.getIcon().clone())
					.name("&6&l" + event.getDisplayName())
					.lore(lore)
					.build();
		}

		@Override
		public void clicked(Player player, ClickType clickType) {
			if (event.canHost(player)) {
				player.chat("/host " + event.getDisplayName());
			} else {
				player.sendMessage(ChatColor.RED + "You cannot host that event.");
			}
		}

	}

}
