package com.theendercore.biome_tag_villagers.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

import static com.theendercore.biome_tag_villagers.BiomeTagVillagers.getBiomeTagToType;

@Mixin(VillagerType.class)
public class VillagerTypeMixin {
    @Unique
    private final static Random biome_tag$random = Random.create();

    @ModifyReturnValue(method = "forBiome", at = @At("RETURN"))
    private static RegistryKey<VillagerType> typeFromBiomeTag(RegistryKey<VillagerType> original, RegistryEntry<Biome> biomeEntry) {
        var types = getBiomeTagToType().entrySet().stream()
                .filter(pair -> biomeEntry.isIn(pair.getKey()))
                .map(Map.Entry::getValue)
                .toList();

        if (!types.isEmpty()) {
            return types.get(biome_tag$random.nextBetween(0, types.size() - 1));
        }
        if (original.getValue().getNamespace().equals(Identifier.DEFAULT_NAMESPACE)) {
            return VillagerType.PLAINS;
        }
        return original;
    }
}
