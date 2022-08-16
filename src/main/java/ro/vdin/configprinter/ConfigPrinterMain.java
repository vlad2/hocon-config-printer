package ro.vdin.configprinter;

import com.google.common.io.Resources;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ConfigPrinterMain {
    @AllArgsConstructor
    static class MainConfig {
        File file;
        boolean verboseOutput;
        boolean resolveSubstitutions;
    }

    public static void main(String[] args) throws IOException {
        try {
            MainConfig cmdLineConfig = parseCmdlineArgs(args);
            ConfigPrinter cp = new ConfigPrinter();
            cp.printConfig(cmdLineConfig.file, cmdLineConfig.verboseOutput, cmdLineConfig.resolveSubstitutions);
        } catch (IllegalArgumentException e) {
            showUsage();
        }
    }

    private static void showUsage() throws IOException {
        URL url = Resources.getResource("usage.txt");
        String text = Resources.toString(url, StandardCharsets.UTF_8);
        System.err.println(text);
    }

    private static MainConfig parseCmdlineArgs(String[] args) throws IOException {
        if (args.length < 1) {
            throw new IllegalArgumentException();
        }

        if (args[0].equals("-help") || args[0].equals("--help")) {
            throw new IllegalArgumentException();
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + file);
        }

        if (file.isDirectory()) {
            throw new IOException("File " + file + " is a directory, not a normal file!");
        }

        boolean verboseOutput = false;
        boolean resolveSubstitutions = false;

        for (int i = 1; i < args.length; i++) {
            String arg = args[i];

            if (arg.equals("-verbose")) {
                verboseOutput = true;
            } else if (arg.equals("-resolve")) {
                resolveSubstitutions = true;
            }
        }

        return new MainConfig(file, verboseOutput, resolveSubstitutions);
    }
}
