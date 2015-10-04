package com.molina.cvmfs.client;

import net.fusejna.FuseException;

import java.io.File;

/**
 * @author Jose Molina Colmenero
 */
public class LaunchFS {

    public static void main(String[] args) throws FuseException {
        if (args.length < 2) {
            System.err.println("Please specify url of the repository " +
                    "and the mount point");
        }
        String repoUrl = args[0];
        String mountpoint = args[1];
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
