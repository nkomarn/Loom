package org.bukkit.craftbukkit.inventory;

import com.google.common.base.Preconditions;
import java.util.List;

import net.minecraft.village.TradeOffer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

public class CraftMerchantRecipe extends MerchantRecipe {

    private final TradeOffer handle;

    public CraftMerchantRecipe(TradeOffer merchantRecipe) {
        super(CraftItemStack.asBukkitCopy(merchantRecipe.sellItem), 0);
        this.handle = merchantRecipe;
        addIngredient(CraftItemStack.asBukkitCopy(merchantRecipe.firstBuyItem));
        addIngredient(CraftItemStack.asBukkitCopy(merchantRecipe.secondBuyItem));
    }

    public CraftMerchantRecipe(ItemStack result, int uses, int maxUses, boolean experienceReward, int experience, float priceMultiplier) {
        super(result, uses, maxUses, experienceReward, experience, priceMultiplier);
        this.handle = new TradeOffer(
                net.minecraft.item.ItemStack.EMPTY,
                net.minecraft.item.ItemStack.EMPTY,
                CraftItemStack.asNMSCopy(result),
                uses,
                maxUses,
                experience,
                priceMultiplier,
                this
        );
        this.setExperienceReward(experienceReward);
    }

    @Override
    public int getUses() {
        return handle.getUses();
    }

    @Override
    public void setUses(int uses) {
        handle.uses = uses;
    }

    @Override
    public int getMaxUses() {
        return handle.getMaxUses();
    }

    @Override
    public void setMaxUses(int maxUses) {
        handle.maxUses = maxUses;
    }

    @Override
    public boolean hasExperienceReward() {
        return handle.rewardingPlayerExperience;
    }

    @Override
    public void setExperienceReward(boolean flag) {
        handle.rewardingPlayerExperience = flag;
    }

    @Override
    public int getVillagerExperience() {
        return handle.traderExperience;
    }

    @Override
    public void setVillagerExperience(int villagerExperience) {
        handle.traderExperience = villagerExperience;
    }

    @Override
    public float getPriceMultiplier() {
        return handle.priceMultiplier;
    }

    @Override
    public void setPriceMultiplier(float priceMultiplier) {
        handle.priceMultiplier = priceMultiplier;
    }

    public TradeOffer toMinecraft() {
        List<ItemStack> ingredients = getIngredients();
        Preconditions.checkState(!ingredients.isEmpty(), "No offered ingredients");
        handle.firstBuyItem = CraftItemStack.asNMSCopy(ingredients.get(0));
        if (ingredients.size() > 1) {
            handle.secondBuyItem = CraftItemStack.asNMSCopy(ingredients.get(1));
        }
        return handle;
    }

    public static CraftMerchantRecipe fromBukkit(MerchantRecipe recipe) {
        if (recipe instanceof CraftMerchantRecipe) {
            return (CraftMerchantRecipe) recipe;
        } else {
            CraftMerchantRecipe craft = new CraftMerchantRecipe(recipe.getResult(), recipe.getUses(), recipe.getMaxUses(), recipe.hasExperienceReward(), recipe.getVillagerExperience(), recipe.getPriceMultiplier());
            craft.setIngredients(recipe.getIngredients());

            return craft;
        }
    }
}
