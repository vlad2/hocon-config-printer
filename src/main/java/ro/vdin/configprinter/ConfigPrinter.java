package ro.vdin.configprinter;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigRenderOptions;

import java.io.File;
import java.io.IOException;

public class ConfigPrinter {
    public void printConfig(File file, boolean verboseOutput, boolean resolveSubstitutions) throws IOException {
        Config config = ConfigFactory.parseFile(file);

        if (resolveSubstitutions) {
            config = config.resolve();
        }

        if (!config.isResolved() && !verboseOutput) {
            System.err.println("WARN: Config not entirely resolved, output won't be fully JSON compatible");
        }

        ConfigRenderOptions renderOpts = verboseOutput ? ConfigRenderOptions.defaults() : ConfigRenderOptions.concise().setFormatted(true);
        String output = config.root().render(renderOpts);
        System.out.println(output);
    }
}
