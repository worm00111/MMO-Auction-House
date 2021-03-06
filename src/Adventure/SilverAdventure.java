/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adventure;

import FileReadWrite.ReadWriteControl;
import Player.Player;
import Item.Item;
import Item.ItemFactory;
import Item.ItemFactoryInterface;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Neil
 */
public class SilverAdventure implements IAdventure {
    ArrayList<String> tiers = new ArrayList<String>();
    Player player = null;
    
    public SilverAdventure(Player player)
    {
        this.player = player;
    }
    
    @Override
    public Item createItem(String tier) {
        ItemFactoryInterface itemFactory = new ItemFactory();
        return itemFactory.createRandomItem(tier);
    }

    @Override
    public boolean calculateRisk() {
        if (checkCanPlay())
        {
            double risk = 0;
		if (player != null)
		{
			risk = player.getRisk() * 0.8;
		}
		double result = Math.random() * 1;

		if (risk > result)
		{
			//System.out.println("You win");
			Item testItem = createItem("Silver");
                        player.addItem(testItem);
                        ReadWriteControl.updateInventory(player.getUsername(), player.getInventory());
                        return true;
		}
		else
		{
			//System.out.println("You lose!");
			//cost to be decided -- for silver failure 
                        int cost = 0;
                        if (player.getWallet().getSilverCoins() - cost < 0)
                        {
                            //sets players amount of silver coins to zero
                            player.getWallet().reduceAmount(0 ,player.getWallet().getSilverCoins());
                        }
                        else
                            player.getWallet().reduceAmount(0, cost);
                        return false;
		}
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Player is not appropriate tier to play.");
        }
        return false;
    }

    @Override
    public void addTierCanPlay(String tier) {
        tiers.add(tier);
    }

    @Override
    public boolean checkCanPlay() {
        for (int i = 0; i < tiers.size(); i++)
        {
            if (tiers.get(i).equals(player.getTier()))
                return true;
        }
        return false;
    }
}
