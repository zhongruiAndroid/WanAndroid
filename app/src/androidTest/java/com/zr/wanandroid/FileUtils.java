package com.zr.wanandroid;


import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileUtils {
    /**
     * 解压Zip
     *
     * @param sourceZipFile 源文件
     * @param savePath      解压后的文件路径
     */
    public static void unZip(File sourceZipFile, String savePath, boolean isDeleteZip) {
        // 判断源文件是否存在
        if (sourceZipFile == null || !sourceZipFile.exists()) {
            return;
        }
        // 开始解压
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(sourceZipFile);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = savePath + "/" + entry.getName();
                    System.out.println(savePath+"|文件夹|"+entry.getName());
                    File dir = new File(dirPath);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(savePath + "/" + entry.getName());
                    System.out.println(savePath+"|文件|"+entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[1024 * 2];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (isDeleteZip) {
            deleteFile(sourceZipFile);
        }
    }

    private static boolean deleteFile(File f) {
        return f != null && f.exists() && !f.isDirectory() ? f.delete() : false;
    }
    public static boolean delete(File file) {
        try {
            if (file == null) return false;
            if (file.isDirectory()) {
                return deleteDir(file);
            }
            return deleteFile(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private static boolean deleteDir(File dir) {
        if (dir == null) return false;
        if (!dir.exists()) return true;
        if (!dir.isDirectory()) return false;
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!file.delete()) return false;
                } else if (file.isDirectory()) {
                    if (!deleteDir(file)) return false;
                }
            }
        }
        return dir.delete();
    }

    public static String readStringFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        StringBuffer sb = new StringBuffer();
        try {
            fis = new FileInputStream(file);//通过字节流获取
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            String line;
            sb.append(br.readLine());
            while ((line = br.readLine()) != null) {
                sb.append("\n" + line);
            }
            br.close();
            isr.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    /**
     * 获取文件md5值
     *
     * @param file
     * @return
     */
    public static String getFileMD5(File file) {
        if (file == null || !file.isFile()) {
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            FileInputStream inputStream = new FileInputStream(file);
            byte buffer[] = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            inputStream.close();
            return bytesToHexString(digest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static String getFileNameByUrl(String fileDownloadUrl) {
        if (TextUtils.isEmpty(fileDownloadUrl)) {
            return "";
        }
        fileDownloadUrl = fileDownloadUrl.split("\\?")[0];
        int index = fileDownloadUrl.lastIndexOf("/");
        String fileName = fileDownloadUrl.substring(index);
        return fileName;
    }
    public static boolean createFileByDeleteOldFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists() && !file.delete()) {
            return false;
        }
        boolean b = file.getParentFile().exists() ? file.getParentFile().isDirectory() : file.getParentFile().mkdirs();
        if (!b){
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
