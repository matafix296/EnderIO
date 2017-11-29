package crazypants.enderio.machines.machine.enchanter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import crazypants.enderio.Log;
import net.minecraft.item.ItemStack;

public class EnchanterRecipeManager {

  private static EnchanterRecipeManager instance = new EnchanterRecipeManager();
  
  public static EnchanterRecipeManager getInstance() {
    return instance;
  }

  @Nonnull
  private final List<EnchanterRecipe> recipes = new ArrayList<EnchanterRecipe>();
    
  public void loadRecipesFromConfig() {
    List<EnchanterRecipe> res = EnchanterRecipeParser.loadRecipeConfig();
    if(res != null) {
      recipes.addAll(res);
    }
    Log.info("Loaded " + recipes.size() + " recipes for Enchanter");
  }

  public EnchanterRecipe getEnchantmentRecipeForInput(ItemStack itemStack) {
    if(itemStack.isEmpty()) {
      return null;
    }
    for(EnchanterRecipe recipe : recipes) {
      if(recipe.isInput(itemStack)) {
        return recipe;
      }
    }
    return null;
  }

  public void addCustomRecipes(String xml) {
    try {
      List<EnchanterRecipe> newRec = EnchanterRecipeParser.parse(xml);
      EnchanterRecipeParser.merge(recipes, newRec);
    } catch (Exception e) {
      Log.error("EnchanterRecipeManager: Error processing custom Enchanter recipes: " + e);
    }
  }

  @Nonnull
  public List<EnchanterRecipe> getRecipes() {
    return recipes;
  }

}