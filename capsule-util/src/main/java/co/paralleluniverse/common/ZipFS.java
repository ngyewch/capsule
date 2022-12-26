/*
 * Capsule
 * Copyright (c) 2014-2015, Parallel Universe Software Co. All rights reserved.
 * 
 * This program and the accompanying materials are licensed under the terms 
 * of the Eclipse Public License v1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package co.paralleluniverse.common;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public final class ZipFS {

    public static FileSystem newZipFileSystem(Path path) throws IOException {
        return FileSystems.newFileSystem(path, null);
    }

    private ZipFS() {
    }
}
