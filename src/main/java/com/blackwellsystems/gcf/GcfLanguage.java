package com.blackwellsystems.gcf;

import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;

public final class GcfLanguage extends Language {

    public static final GcfLanguage INSTANCE = new GcfLanguage();

    private GcfLanguage() {
        super("GCF");
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "GCF - Graph Compact Format";
    }

    @Override
    public boolean isCaseSensitive() {
        return true;
    }
}
