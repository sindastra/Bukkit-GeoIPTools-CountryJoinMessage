/**
 * Bukkit-GeoIPTools-CountryJoinMessage
 * Copyright (C) 2016 Sindastra <https://github.com/sindastra>
 * All rights reserved.
 *
 * This and the above copyright notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * This software is not affiliated with Bukkit nor GeoIPTools.
 * 
 * @author Sindastra
 * @copyright Copyright (C) 2016 Sindastra. All rights reserved.
 */

package io.github.sindastra.BukkitGeoIPToolsCountryJoinMessage;

import java.net.InetAddress;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import uk.org.whoami.geoip.GeoIPLookup;
import uk.org.whoami.geoip.GeoIPTools;

public class Main extends JavaPlugin implements Listener
{
	private GeoIPLookup getGeoIPLookup()
	{
		Plugin geoipPlugin = getServer().getPluginManager().getPlugin("GeoIPTools");
		return ((GeoIPTools) geoipPlugin).getGeoIPLookup();
	}
	
	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
		
		getLogger().info("Enabled!");
	}
	
	@Override
	public void onDisable()
	{
		getLogger().info("Disabled.");
	}
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event)
	{
		String playerName = event.getPlayer().getName();
		InetAddress playerInetAddress = event.getPlayer().getAddress().getAddress();
		String playerCountry = getGeoIPLookup().getCountry(playerInetAddress).getName();
		
		event.setJoinMessage( ChatColor.BOLD+""+ChatColor.GOLD+ playerName +ChatColor.YELLOW+
				" has joined from " +ChatColor.GOLD+ playerCountry );
	}
}
