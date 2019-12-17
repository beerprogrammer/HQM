package hqm.item;

import hqm.HQM;
import hqm.Registry;
import net.minecraft.item.Item;
import net.minecraft.util.NonNullList;

/**
 * @author canitzp
 */
public class ItemBase<T extends ItemBase> extends Item {

    public static final NonNullList<ItemBase> ITEMS = NonNullList.create();

    public ItemBase(String name){
        super(new Properties());
        this.setRegistryName(HQM.MODID, name);
        //this.setUnlocalizedName(this.getRegistryName().toString());
        //this.setCreativeTab(Registry.TAB);
    }

    public T register(){
        ITEMS.add(this);
        return (T) this;
    }

}