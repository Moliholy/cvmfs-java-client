package com.molina.cvmfs.client;

import net.fusejna.FuseException;

import java.io.File;

/**
 * @author Jose Molina Colmenero
 */
public class LaunchFS {

    public static void main(String[] args) throws FuseException {
        String repoUrl = args[1];
        String mountpoint = args[2];
        String repoCache = "/tmp/cvmfs-java-client-cache";
        if (args.length > 2)
            repoCache = args[2];
        CvmfsFileSystem fs = new CvmfsFileSystem(repoUrl, repoCache);
        File mountpointFile = new File(mountpoint);
        if (mountpointFile.exists() && mountpointFile.isDirectory()) {
            fs.mount(mountpointFile);
        } else {
            System.err.println("Cannot mount the file system in "
                    + mountpointFile.getAbsolutePath());
        }
    }
}
