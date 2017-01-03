package com.kiran.directoryViewer.model.fetcher;

import com.kiran.directoryViewer.model.AbstractDiskResident;
import com.kiran.directoryViewer.model.DiskResident;
import com.kiran.directoryViewer.model.DiskResident.Type;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by Kiran Kolli on 01-01-2017.
 */
public interface NodeCreationStrategy {
    DirectoryStructureNode createNode();

    default BasicFileAttributes getBasicFileAttributes(Path path) throws IOException {
        return Files.getFileAttributeView(path, BasicFileAttributeView.class).readAttributes();
    }

    default DummyNode getDummyNode(final Path path, final DirectoryStructureNode parent) throws IOException {
        if (Files.isDirectory(path)) {
            return getDummyNode(path, Type.DIRECTORY, parent);
        } else {
            return getDummyNode(path, Type.FILE, parent);
        }
    }

    default DummyNode getDummyNode(final Path path, final Type type, final DirectoryStructureNode parent) throws IOException {
        BasicFileAttributes attributes = getBasicFileAttributes(path);
        return new DummyNode(path, parent) {
            @Override
            public DiskResident toDiskResident() {
                return new AbstractDiskResident(path, attributes.size(), attributes.creationTime(), attributes.lastModifiedTime()) {
                    @Override
                    public Type getType() {
                        return type;
                    }
                };
            }
        };
    }
}
