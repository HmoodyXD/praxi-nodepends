package lol.vifez.praxi.kit;


import lol.vifez.praxi.util.command.command.adapter.CommandTypeAdapter;

public class KitTypeAdapter implements CommandTypeAdapter {

	@Override
	public <T> T convert(String string, Class<T> type) {
		return type.cast(Kit.getByName(string));
	}

}
