package com.theendercore.biome_tag_villagers.mixin;

import com.theendercore.biome_tag_villagers.BiomeTagVillagers;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biome;

public class ExampleMod implements ModInitializer {
    public static final RegistryKey<VillagerType> NETHER_TYPE = create(Identifier.of("exmaple", "nether"));
    public static final TagKey<Biome> NETHER_BIOME_TAG = TagKey.of(RegistryKeys.BIOME, Identifier.of("exmaple", "nether_biomes"));

    @Override
    public void onInitialize() {
        BiomeTagVillagers.addType(NETHER_BIOME_TAG, NETHER_TYPE);
    }

    private static RegistryKey<VillagerType> create(Identifier id) {
        var key = RegistryKey.of(RegistryKeys.VILLAGER_TYPE, id);
        Registry.register(Registries.VILLAGER_TYPE, key, new VillagerType());
        return key;
    }
}
