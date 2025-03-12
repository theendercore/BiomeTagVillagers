package com.theendercore.biome_tag_villagers;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biome;

public class ExampleMod implements ModInitializer {
    public static final VillagerType NETHER_TYPE = create(Identifier.of("exmaple", "nether"));
    public static final TagKey<Biome> NETHER_BIOME_TAG = TagKey.of(RegistryKeys.BIOME, Identifier.of("exmaple", "nether_biomes"));

    @Override
    public void onInitialize() {
        BiomeTagVillagers.addType(NETHER_BIOME_TAG, NETHER_TYPE);
    }

    private static VillagerType create(Identifier id) {
        return Registry.register(Registries.VILLAGER_TYPE, id, new VillagerType(id.toString()));
    }
}
