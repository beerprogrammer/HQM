package hardcorequesting.common.event;

import hardcorequesting.common.HardcoreQuestingCore;
import hardcorequesting.common.quests.QuestLine;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.LevelResource;

import java.nio.file.Path;
import java.util.Optional;

public class WorldEventListener {
    public static void onLoad(ResourceKey<Level> worldRegistryKey, ServerLevel world) {
        if (!world.isClientSide && world.dimension().equals(Level.OVERWORLD)) {
            Path hqm = getWorldPath(world).resolve("hqm");
            QuestLine questLine = QuestLine.reset(Optional.of(HardcoreQuestingCore.packDir), Optional.of(hqm));
            questLine.loadAll();
        }
    }
    
    public static void onSave(ServerLevel world) {
        if (!world.isClientSide && world.dimension().equals(Level.OVERWORLD)) {
            QuestLine.getActiveQuestLine().saveData();
        }
    }
    
    private static Path getWorldPath(ServerLevel world) {
        return HardcoreQuestingCore.platform.getStorageSourceOfServer(world.getServer()).getLevelPath(LevelResource.ROOT).toAbsolutePath().normalize();
    }
    
}
