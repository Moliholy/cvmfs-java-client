package com.molina.cvmfs.client;

import com.molina.cvmfs.directoryentry.DirectoryEntry;
import com.molina.cvmfs.repository.Repository;
import com.molina.cvmfs.repository.exception.CacheDirectoryNotFound;
import com.molina.cvmfs.repository.exception.FailedToLoadSourceException;
import com.molina.cvmfs.rootfile.exception.RootFileException;
import net.fusejna.*;
import net.fusejna.util.FuseFilesystemAdapterFull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jose Molina Colmenero
 *         <p/>
 *         This interface cannot handle inodes, so some operations are not
 *         identical to the original CernVM-FS. Concretely lookup and forget functions
 *         are not supported
 */
public class CvmfsFileSystem extends FuseFilesystemAdapterFull {

    private Repository repository;
    private String cachePath;
    private String url;

    private Map<String, FileInputStream> openedFiles;

    public CvmfsFileSystem(String url, String cachePath) {
        this.cachePath = cachePath;
        this.url = url;
    }

    @Override
    public void init() {
        openedFiles = new HashMap<String, FileInputStream>();
        try {
            repository = new Repository(url, cachePath);
        } catch (FailedToLoadSourceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CacheDirectoryNotFound cacheDirectoryNotFound) {
            cacheDirectoryNotFound.printStackTrace();
        } catch (RootFileException e) {
            e.printStackTrace();
        }
        if (repository == null)
            System.exit(-1);
        log(true);
    }

    @Override
    public void destroy() {
    }

    @Override
    public int getattr(String path, StructStat.StatWrapper stat) {
        DirectoryEntry result = repository.lookup(path);
        if (result != null) {
            stat
                    .dev(1).rdev(1)
                    .uid(1).gid(1)
                    .mode(result.getMode()).nlink(1)
                    .size(result.getSize()).blksize(4096)
                    .blocks(1 + result.getSize() / 512)
                    .atime(result.getMtime())
                    .mtime(result.getMtime())
                    .ctime(result.getMtime());
            return 0;
        }
        return ErrorCodes.ENOENT();
    }

    @Override
    public int readlink(String path, ByteBuffer buffer, long size) {
        DirectoryEntry result = repository.lookup(path, false);
        if (result != null) {
            if (result.isSymplink()) {
                buffer.put(result.getSymlink().getBytes());
                return 0;
            } else {
                return ErrorCodes.ENOLINK();
            }
        }
        return ErrorCodes.ENOENT();
    }

    @Override
    public int open(String path, StructFuseFileInfo.FileInfoWrapper info) {
        File result = repository.getFile(path);
        if (result != null) {
            if (result.isFile()) {
                try {
                    FileInputStream fis = new FileInputStream(result);
                    openedFiles.put(path, fis);
                    info
                            .keep_cache(false)
                            .fh(fis.getFD().hashCode());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    ErrorCodes.ENOENT();
                } catch (IOException e) {
                    e.printStackTrace();
                    ErrorCodes.ENOENT();
                }
                return 0;
            } else {
                return ErrorCodes.ENOENT();
            }
        }
        return ErrorCodes.ENOENT();
    }

    @Override
    public int read(String path, ByteBuffer buffer, long size, long offset,
                    StructFuseFileInfo.FileInfoWrapper info) {
        FileInputStream fis = openedFiles.get(path);
        if (fis != null) {
            int sizeInt = new Long(size).intValue();
            byte[] bytes = new byte[sizeInt];
            try {
                int bytesRead = fis.read(bytes, ((int) offset), sizeInt);
                if (bytesRead == sizeInt) {
                    buffer.put(bytes);
                    return 0;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ErrorCodes.ENOENT();
    }

    @Override
    public int release(String path, StructFuseFileInfo.FileInfoWrapper info) {
        FileInputStream fis = openedFiles.remove(path);
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                return ErrorCodes.ENOENT();
            }
            return 0;
        }
        return ErrorCodes.ENOENT();
    }

    @Override
    public int readdir(String path, DirectoryFiller filler) {
        DirectoryEntry result = repository.lookup(path);
        if (result != null) {
            if (result.isDirectory()) {
                return 0;
            } else {
                return ErrorCodes.ENOTDIR();
            }
        } else {
            return ErrorCodes.ENOENT();
        }
    }

    @Override
    public int statfs(String path, StructStatvfs.StatvfsWrapper wrapper) {
        return 0;
    }

    @Override
    public int getxattr(String path, String xattr, XattrFiller filler, long size, long position) {
        // NOTE: not handled at the moment
        return super.getxattr(path, xattr, filler, size, position);
    }

    @Override
    public int listxattr(String path, XattrListFiller filler) {
        // NOTE: not handled at the moment
        return super.listxattr(path, filler);
    }

}
