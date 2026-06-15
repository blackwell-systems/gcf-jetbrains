package com.blackwellsystems.gcf;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.textmate.api.TextMateBundleProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.Collections;

/**
 * Provides the GCF TextMate grammar bundle to the JetBrains TextMate plugin.
 */
public final class GcfTextMateBundleProvider implements TextMateBundleProvider {

    @NotNull
    @Override
    public Collection<PluginBundle> getBundles() {
        try {
            Path bundleDir = extractBundle();
            return Collections.singleton(new PluginBundle("GCF", bundleDir));
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private Path extractBundle() throws IOException {
        Path tempDir = Files.createTempDirectory("gcf-textmate-bundle");
        tempDir.toFile().deleteOnExit();

        Path syntaxesDir = tempDir.resolve("syntaxes");
        Files.createDirectories(syntaxesDir);

        // Extract the grammar file from plugin resources
        try (InputStream is = getClass().getResourceAsStream("/syntaxes/gcf.tmLanguage.json")) {
            if (is == null) {
                throw new IOException("GCF grammar resource not found");
            }
            Path grammarFile = syntaxesDir.resolve("gcf.tmLanguage.json");
            Files.copy(is, grammarFile, StandardCopyOption.REPLACE_EXISTING);
            grammarFile.toFile().deleteOnExit();
        }

        // Write the package.json that TextMate bundle loading expects
        Path packageJson = tempDir.resolve("package.json");
        Files.writeString(packageJson, """
                {
                  "name": "gcf",
                  "contributes": {
                    "languages": [{
                      "id": "GCF",
                      "aliases": ["GCF", "Graph Compact Format"],
                      "extensions": [".gcf"]
                    }],
                    "grammars": [{
                      "language": "GCF",
                      "scopeName": "source.gcf",
                      "path": "./syntaxes/gcf.tmLanguage.json"
                    }]
                  }
                }
                """);
        packageJson.toFile().deleteOnExit();
        syntaxesDir.toFile().deleteOnExit();

        return tempDir;
    }
}
