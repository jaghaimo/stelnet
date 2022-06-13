package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.ModSpecAPI;
import java.io.PrintWriter;
import lombok.extern.log4j.Log4j;

@Log4j
public class Reporter {

    public static void generate() {
        PrintWriter writer;
        try {
            writer = new PrintWriter(ModConstants.STELNET_LOG);
            writeProperties(writer);
            writer.println("");
            writeMods(writer);
            writer.close();
        } catch (Exception e) {
            log.error("Could not write to file", e);
        }
    }

    private static void writeProperties(PrintWriter writer) {
        writer.println("System properties".toUpperCase());
        writer.println("Java version: " + System.getProperty("java.version"));
        writer.println("Java vendor: " + System.getProperty("java.vendor"));
    }

    private static void writeMods(PrintWriter writer) {
        writer.println("Enabled mods".toUpperCase());
        for (ModSpecAPI mod : Global.getSettings().getModManager().getEnabledModsCopy()) {
            writer.println(String.format("%s-%s: %s", mod.getId(), mod.getVersion(), mod.getName()));
        }
    }
}
