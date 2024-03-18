package me.lunatic.lunaui.util;

import me.lunatic.lunaui.io.JsonConfiguration;
import me.lunatic.lunaui.io.MemoryConfiguration;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class ResourceUtil {

    private static MinecraftClient client = MinecraftClient.getInstance();

    public static MemoryConfiguration extractJsonConfiguration(Identifier cfg) {
        MemoryConfiguration memCfg = new MemoryConfiguration();
        for (Map.Entry<Identifier, List<Resource>> entry : client.getResourceManager().findAllResources(cfg.getPath(), cfg::equals).entrySet()) {
            for (Resource res : entry.getValue()) {
                try (InputStream resStream = res.getInputStream()) {
                    File file = Files.createTempFile(null, null).toFile();
                    file.deleteOnExit();
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(resStream.readAllBytes());
                        JsonConfiguration propCfg = new JsonConfiguration(file, true);
                        propCfg.getValues().forEach(memCfg::set);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return memCfg;
    }
}
