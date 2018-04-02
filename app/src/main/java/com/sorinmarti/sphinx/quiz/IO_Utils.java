package com.sorinmarti.sphinx.quiz;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SOMA on 21.10.2017.
 */

public class IO_Utils {

    public static final String DATA_FOLDER = "qdata";
    public static final String DOWNLOAD_BASE_URL = "http://www.sorinmarti.com/sphinx/quiz_files/";

    public static void downloadAndSaveFile(String localFolderName, String localFilename, String remoteUrlName, Context context) throws Exception {
        String remoteFileContents = getRemoteFile(remoteUrlName);
        saveFile( localFolderName, localFilename, remoteFileContents, context );
    }

    public static void createEmptyFile(String folderName, String filename, Context context) throws Exception {
        saveFile(folderName, filename, "", context);
    }

    public static void saveFile(String folderName, String filename, String contents, Context context) throws Exception {
        ContextWrapper cw = new ContextWrapper(context);
        File folder = cw.getDir(folderName, Context.MODE_PRIVATE);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file = new File(folder, filename);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(contents);
            osw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteFile(String folderName, String filename, Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        File folder = cw.getDir(folderName, Context.MODE_PRIVATE);
        File file = new File(folder, filename);
        return file.delete();
    }

    public static boolean fileExists(String folderName, String filename, Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        File folder = cw.getDir(folderName, Context.MODE_PRIVATE);
        File file = new File(folder, filename);
        return file.exists();
    }

    public static String[] getFileList(final String folderName, final String ending, Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        File folder = cw.getDir(folderName, Context.MODE_PRIVATE);
        String[] files = folder.list(new FilenameFilter() {
            @Override
            public boolean accept(File file, String filename) {
                if(filename.equals(".") || filename.equals("..")) {
                    return false;
                }
                if(ending.equals("")) {
                    return true;
                }
                return filename.endsWith(ending);
            }
        });
        return files;
    }


    public static String getRemoteFile(String urlString) throws Exception {
        List<String> lines = getRemoteFileLineList(urlString);
        String fileText = "";
        for(String line : lines) {
            fileText += line+"\n";
        }
        return fileText;
    }

    public static List<String> getRemoteFileLineList(String urlString) throws Exception {
        List<String> lines = new ArrayList<>();

        try {
            // Create a URL for the desired page
            URL url = new URL( urlString );

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            while ((str = in.readLine()) != null) {
                lines.add(str);
            }
            in.close();
        } catch (MalformedURLException e) {
            throw new Exception("The URL is malformed.");
        } catch (IOException e) {
            throw new Exception("The file could not be retrieved.");
        }
        return lines;
    }



    public static String getLocalFile(String folderName, String filename, Context context) throws Exception {
        List<String> lines = null;
        try {
            lines = getLocalFileLineList(folderName, filename, context);
        } catch (Exception e) {
            throw e;
        }
        String fileText = "";
        for(String line : lines) {
            fileText += line+"\n";
        }
        return fileText;
    }

    public static List<String> getLocalFileLineList(String folderName, String filename, Context context) throws Exception {
        BufferedReader reader = null;
        List<String> lines = new ArrayList<>();

        ContextWrapper cw = new ContextWrapper(context);
        File folder = cw.getDir(folderName, Context.MODE_PRIVATE);
        File file = new File(folder, filename);
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new Exception("Could not get local file");
        }

        return lines;
    }

    public static void downloadImage(String _url, String folderName, String fileName, Context context)
    {
        //Prepare to download image
        URL url;
        InputStream in;
        BufferedInputStream buf;

        try {
            url = new URL(_url);
            in = url.openStream();

            // Read the inputstream
            buf = new BufferedInputStream(in);

            // Convert the BufferedInputStream to a Bitmap
            Bitmap bMap = BitmapFactory.decodeStream(buf);
            if (in != null) {
                in.close();
            }
            if (buf != null) {
                buf.close();
            }

            saveImage(folderName, fileName, bMap, context);

        } catch (Exception e) {
            Log.e("Error reading file", e.toString());
        }
    }

   public static void saveImage(String folderName, String fileName, Bitmap bitmap, Context context) {
       ContextWrapper cw = new ContextWrapper(context);
       File directory = cw.getDir(folderName, Context.MODE_PRIVATE);
       if (!directory.exists()) {
           directory.mkdir();
       }
       File myPath = new File(directory, fileName);

       FileOutputStream fos = null;
       try {
           fos = new FileOutputStream(myPath);
           bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
           fos.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

    public static Bitmap getLocalBitmap(String folderName, String filename, Context context) throws Exception {
        ContextWrapper cw = new ContextWrapper(context);
        File imageDir = cw.getDir(folderName, Context.MODE_PRIVATE);
        File imageFile = new File(imageDir, filename);
        Bitmap bMap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        return bMap;
    }

    public static String getMD5(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());

        byte byteData[] = md.digest();


        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<byteData.length;i++) {
            String hex=Integer.toHexString(0xff & byteData[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }


}
