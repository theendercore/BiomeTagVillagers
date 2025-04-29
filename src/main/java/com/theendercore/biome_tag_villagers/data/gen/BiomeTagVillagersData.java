package com.theendercore.biome_tag_villagers.data.gen;

import com.theendercore.biome_tag_villagers.BiomeTagVillagers;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;

import static com.theendercore.biome_tag_villagers.BiomeTagVillagers.log;

@SuppressWarnings("unused")
public class BiomeTagVillagersData implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator gen) {
        log.info("Hello from DataGen");
        var pack = gen.createPack();

        pack.addProvider(BiomeTagProvider::new);
    }

    static class BiomeTagProvider extends FabricTagProvider<Biome> {
        public BiomeTagProvider(FabricDataOutput o, CompletableFuture<RegistryWrapper.WrapperLookup> r) {
            super(o, RegistryKeys.BIOME, r);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(BiomeTagVillagers.VILLAGER_DESERT)
                    .forceAddTag(ConventionalBiomeTags.IS_DESERT)
                    .forceAddTag(ConventionalBiomeTags.IS_BADLANDS);
            getOrCreateTagBuilder(BiomeTagVillagers.VILLAGER_JUNGLE)
                    .forceAddTag(ConventionalBiomeTags.IS_JUNGLE);
            getOrCreateTagBuilder(BiomeTagVillagers.VILLAGER_SAVANNA)
                    .forceAddTag(ConventionalBiomeTags.IS_SAVANNA);
            getOrCreateTagBuilder(BiomeTagVillagers.VILLAGER_SNOWY)
                    .forceAddTag(ConventionalBiomeTags.IS_SNOWY)
                    .forceAddTag(ConventionalBiomeTags.IS_AQUATIC_ICY);
            getOrCreateTagBuilder(BiomeTagVillagers.VILLAGER_SWAMP)
                    .forceAddTag(ConventionalBiomeTags.IS_SWAMP);
            getOrCreateTagBuilder(BiomeTagVillagers.VILLAGER_TAIGA)
                    .forceAddTag(ConventionalBiomeTags.IS_TAIGA);
        }
    }
}
