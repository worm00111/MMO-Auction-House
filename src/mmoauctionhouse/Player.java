/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmoauctionhouse;

/**
 *
 * @author Vilius
 */
public abstract class Player {
    private double tax;
    private Wallet wallet;
    private String tier;
    private Inventory inventory;
    private String username;
    
    public abstract int calculateTax(int cost);
    public Wallet getWallet() {
        return wallet;
    }
    
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
    
    public void setWallet(int startingMoney) {
        wallet = new Wallet(startingMoney);
    }
    
    public void setTier(String tier) {
        this.tier = tier;
    }
    
    public String getTier()
    {
        return tier;
    }
    
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setTax(double tax) {
        this.tax = tax;
    }
    
    public double getTax() {
        return tax;
    }
    
    public String toString() {
        int numOfItems = inventory.getNumOfItems();
        String result = "Player = " + username;
        result += "\nTier = " + tier;
        result += "\nTax = " + tax;
        result += "\nCoins(Bronze/Silver/Gold) = (" + wallet.getBronzeCoins() + "/" + wallet.getSilverCoins() + "/" + wallet.getGoldCoins() + ")";
        result += "\n" + numOfItems + " items:";
        
        result += inventory.toString();
        
        return result;
    }
    
    public void addItem(Item item)
    {
        inventory.addItem(item);
    }
    
    public abstract double getRisk();
    public Inventory getInventory(){
        return this.inventory ;
    }
}
