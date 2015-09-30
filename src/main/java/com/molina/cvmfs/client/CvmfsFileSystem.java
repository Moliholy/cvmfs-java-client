package com.molina.cvmfs.client;

import com.molina.cvmfs.repository.Repository;
import net.fusejna.*;
import net.fusejna.util.FuseFilesystemAdapterFull;

import java.nio.ByteBuffer;

/**
 * @author Jose Molina Colmenero
 *
 * This interface cannot handle inodes, so some operations are not
 * identical to the original CernVM-FS. Concretely lookup and forget functions
 * are not supported
 */
public class CvmfsFileSystem extends FuseFilesystemAdapterFull {

    private Repository repository;
    private String cachePath;

    public CvmfsFileSystem(String cachePath) {
        this.cachePath = cachePath;
    }

    public CvmfsFileSystem() {
        this.cachePath = "~/.cvmfs-java-cache";
    }


    public FuseFilesystem log() {
        return super.log(true);
    }

    private int printIlegalOperationMessage() {
        System.err.println("Read-only file system!");
        return ErrorCodes.EROFS();
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public int getattr(String path, StructStat.StatWrapper stat) {
        return 0;
    }

    @Override
    public int readlink(String path, ByteBuffer buffer, long size) {
        return 0;
    }

    @Override
    public int open(String path, StructFuseFileInfo.FileInfoWrapper info) {
        return 0;
    }

    @Override
    public int read(String path, ByteBuffer buffer, long size, long offset, StructFuseFileInfo.FileInfoWrapper info) {
        return 0;
    }

    @Override
    public int release(String path, StructFuseFileInfo.FileInfoWrapper info) {
        return 0;
    }

    @Override
    public int opendir(String path, StructFuseFileInfo.FileInfoWrapper info) {
        return 0;
    }

    @Override
    public int readdir(String path, DirectoryFiller filler) {
        return 0;
    }

    @Override
    public int releasedir(String path, StructFuseFileInfo.FileInfoWrapper info) {
        return 0;
    }

    @Override
    public int statfs(String path, StructStatvfs.StatvfsWrapper wrapper) {
        return 0;
    }

    @Override
    public int getxattr(String path, String xattr, XattrFiller filler, long size, long position) {
        // NOTE: not handle at the moment
        return super.getxattr(path, xattr, filler, size, position);
    }

    @Override
    public int listxattr(String path, XattrListFiller filler) {
        // NOTE: not handle at the moment
        return super.listxattr(path, filler);
    }

}
