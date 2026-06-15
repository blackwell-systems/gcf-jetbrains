package com.blackwellsystems.gcf;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.textmate.api.TextMateBundleProvider;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;

public final class GcfTextMateBundleProvider implements TextMateBundleProvider {

    @NotNull
    @Override
    public List<PluginBundle> getBundles() {
        try {
            Path bundleDir = extractBundle();
            return Collections.singletonList(new PluginBundle("GCF", bundleDir));
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private Path extractBundle() throws IOException {
        Path tempDir = Files.createTempDirectory("gcf-textmate-bundle");
        tempDir.toFile().deleteOnExit();

        Path syntaxesDir = tempDir.resolve("syntaxes");
        Files.createDirectories(syntaxesDir);

        try (InputStream is = getClass().getResourceAsStream("/syntaxes/gcf.tmLanguage.json")) {
            if (is == null) {
                throw new IOException("GCF grammar resource not found");
            }
            Path grammarFile = syntaxesDir.resolve("gcf.tmLanguage.json");
            Files.copy(is, grammarFile, StandardCopyOption.REPLACE_EXISTING);
            grammarFile.toFile().deleteOnExit();
        }

        Path packageJson = tempDir.resolve("package.json");
        Files.writeString(packageJson,
            "{\n" +
            "  \"name\": \"gcf\",\n" +
            "  \"contributes\": {\n" +
            "    \"languages\": [{\n" +
            "      \"id\": \"GCF\",\n" +
            "      \"aliases\": [\"GCF\", \"Graph Compact Format\"],\n" +
            "      \"extensions\": [\".gcf\"]\n" +
            "    }],\n" +
            "    \"grammars\": [{\n" +
            "      \"language\": \"GCF\",\n" +
            "      \"scopeName\": \"source.gcf\",\n" +
            "      \"path\": \"./syntaxes/gcf.tmLanguage.json\"\n" +
            "    }]\n" +
            "  }\n" +
            "}\n");
        packageJson.toFile().deleteOnExit();
        syntaxesDir.toFile().deleteOnExit();

        return tempDir;
    }
}
