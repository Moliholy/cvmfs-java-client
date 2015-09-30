package com.molina.cvmfs.client;

import net.fusejna.*;
import net.fusejna.types.TypeMode;
import net.fusejna.util.FuseFilesystemAdapterFull;

import java.nio.ByteBuffer;

/**
 * @author Jose Molina Colmenero
 */
public class CvmfsFileSystem extends FuseFilesystemAdapterFull {

    private com.molina.cvmfs.repository.Repository repository;

    public CvmfsFileSystem


    public FuseFilesystem log() {
        return super.log(true);
    }

    private int printIlegalOperationMessage() {
        System.err.println("Read-only file system!");
        return ErrorCodes.EROFS();
    }


    @Override
    public int access(String s, int i) {
        return 0;
    }

    @Override
    public int chmod(String s, TypeMode.ModeWrapper modeWrapper) {
        return 0;
    }

    @Override
    public int chown(String s, long l, long l1) {
        return 0;
    }

    @Override
    public int create(String s, TypeMode.ModeWrapper modeWrapper, StructFuseFileInfo.FileInfoWrapper fileInfoWrapper) {
        return 0;
    }

    @Override
    public void destroy() {

    }

    @Override
    public int fgetattr(String s, StructStat.StatWrapper statWrapper, StructFuseFileInfo.FileInfoWrapper fileInfoWrapper) {
        return 0;
    }

    @Override
    public int flush(String s, StructFuseFileInfo.FileInfoWrapper fileInfoWrapper) {
        return 0;
    }

    @Override
    public int fsync(String s, int i, StructFuseFileInfo.FileInfoWrapper fileInfoWrapper) {
        return 0;
    }

    @Override
    public int fsyncdir(String s, int i, StructFuseFileInfo.FileInfoWrapper fileInfoWrapper) {
        return 0;
    }

    @Override
    public int ftruncate(String s, long l, StructFuseFileInfo.FileInfoWrapper fileInfoWrapper) {
        return 0;
    }

    @Override
    public int getattr(String s, StructStat.StatWrapper statWrapper) {
        // TODO
        return 0;
    }

    @Override
    protected String getName() {
        return null;
    }

    @Override
    protected String[] getOptions() {
        return new String[0];
    }

    @Override
    public int getxattr(String s, String s1, XattrFiller xattrFiller, long l, long l1) {
        return 0;
    }

    @Override
    public void init() {

    }

    @Override
    public int link(String s, String s1) {
        return 0;
    }

    @Override
    public int listxattr(String s, XattrListFiller xattrListFiller) {
        return 0;
    }

    @Override
    public int lock(String s, StructFuseFileInfo.FileInfoWrapper fileInfoWrapper, FlockCommand flockCommand, StructFlock.FlockWrapper flockWrapper) {
        return 0;
    }

    @Override
    public int mkdir(String s, TypeMode.ModeWrapper modeWrapper) {
        return 0;
    }

    @Override
    public int mknod(String s, TypeMode.ModeWrapper modeWrapper, long l) {
        return 0;
    }

    @Override
    public int open(String s, StructFuseFileInfo.FileInfoWrapper fileInfoWrapper) {
        if(fileInfoWrapper.openMode().equals(StructFuseFileInfo.FileInfoWrapper.OpenMode.READONLY)) {

        }
        return 0;
    }

    @Override
    public int opendir(String s, StructFuseFileInfo.FileInfoWrapper fileInfoWrapper) {
        return 0;
    }

    @Override
    public int read(String s, ByteBuffer byteBuffer, long l, long l1, StructFuseFileInfo.FileInfoWrapper fileInfoWrapper) {
        return 0;
    }

    @Override
    public int readdir(String s, DirectoryFiller directoryFiller) {
        // TODO
        return 0;
    }

    @Override
    public int readlink(String s, ByteBuffer byteBuffer, long l) {
        // TODO
        return 0;
    }

    @Override
    public int release(String s, StructFuseFileInfo.FileInfoWrapper fileInfoWrapper) {
        return 0;
    }

    @Override
    public int releasedir(String s, StructFuseFileInfo.FileInfoWrapper fileInfoWrapper) {
        return 0;
    }

    @Override
    public int removexattr(String s, String s1) {
        return 0;
    }

    @Override
    public int rename(String s, String s1) {
        return 0;
    }

    @Override
    public int rmdir(String s) {
        return 0;
    }

    @Override
    public int setxattr(String s, String s1, ByteBuffer byteBuffer, long l, int i, int i1) {
        return 0;
    }

    @Override
    public int statfs(String s, StructStatvfs.StatvfsWrapper statvfsWrapper) {
        return 0;
    }

    @Override
    public int symlink(String s, String s1) {
        return 0;
    }

    @Override
    public int truncate(String s, long l) {
        return 0;
    }

    @Override
    public int unlink(String s) {
        return 0;
    }

    @Override
    public int utimens(String s, StructTimeBuffer.TimeBufferWrapper timeBufferWrapper) {
        return 0;
    }

    @Override
    public int write(String s, ByteBuffer byteBuffer, long l, long l1, StructFuseFileInfo.FileInfoWrapper fileInfoWrapper) {
        return 0;
    }
}
