package com.blackwellsystems.gcf;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public final class GcfFileType extends LanguageFileType {

    public static final GcfFileType INSTANCE = new GcfFileType();

    private GcfFileType() {
        super(GcfLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "GCF";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "GCF (Graph Compact Format) file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "gcf";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/icons/gcf-logo.png", GcfFileType.class);
    }
}
