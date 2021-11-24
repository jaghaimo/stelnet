package stelnet.util;

import com.fs.starfarer.api.ModSpecAPI;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ReportUtils {

    public static void generate() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(ModConstants.STELNET_LOG);
        writeProperties(writer);
        writer.println("");
        writeMods(writer);
        writer.close();
    }

    private static void writeProperties(PrintWriter writer) {
        writer.println("System properties".toUpperCase());
        writer.println("Java version: " + System.getProperty("java.version"));
        writer.println("Java vendor: " + System.getProperty("java.vendor"));
    }

    private static void writeMods(PrintWriter writer) {
        writer.println("Enabled mods".toUpperCase());
        for (ModSpecAPI mod : SettingsUtils.getEnabledMods()) {
            writer.println(String.format("%s-%s: %s", mod.getId(), mod.getVersion(), mod.getName()));
        }
    }
}
