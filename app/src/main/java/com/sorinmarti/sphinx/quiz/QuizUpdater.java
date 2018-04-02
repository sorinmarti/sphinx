package com.sorinmarti.sphinx.quiz;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SOMA on 20.10.2017.
 */

public class QuizUpdater {


    private static final String LOCAL_MF = "MANIFEST.sphinx";
    private static final String REMOTE_MF = "http://www.sorinmarti.com/sphinx/getManifest.php";

    public static boolean gameNeedsUpdating(Context context) throws Exception {
        if(!IO_Utils.fileExists(IO_Utils.DATA_FOLDER, LOCAL_MF, context)) {
            return true;
        }
        String remoteManifest = IO_Utils.getRemoteFile(REMOTE_MF);
        String localManifest  = IO_Utils.getLocalFile(IO_Utils.DATA_FOLDER, LOCAL_MF, context);

        if(localManifest.equals(remoteManifest)) {
            return false;       // All is up to date
        }

        return true;
    }

    public static void updateAll(Context context) throws Exception {
        for(UpdateAction action : getUpdateActions(context)) {
            action.processAction(context);
        }
    }

    /**
     * Puts together a list of update actions.
     * @param context
     * @return
     * @throws Exception
     */
    public static List<UpdateAction> getUpdateActions(Context context) throws Exception {
        List<UpdateAction> actions = new ArrayList<>();
        List<String> remoteManifest = IO_Utils.getRemoteFileLineList(REMOTE_MF);

        if(!IO_Utils.fileExists(IO_Utils.DATA_FOLDER, LOCAL_MF, context)) {
            IO_Utils.createEmptyFile(IO_Utils.DATA_FOLDER, LOCAL_MF, context);
        }
        List<String> localManifest  = IO_Utils.getLocalFileLineList(IO_Utils.DATA_FOLDER, LOCAL_MF, context);

        for(String quizInfo : remoteManifest) {
            // A line for each quiz file
            // file_name.quiz:md5a23b42e34
            if(quizInfo.split(":").length==2) {
                String[] parts = quizInfo.split(":");
                String filename = parts[0];
                String checksum = parts[1];

                // If the filename is found in the local manifest
                if (fileListContains(localManifest, filename)) {
                    // The file is in the local manifest. Is it the same?
                    String localChecksum = checksumOfFile(localManifest, filename);
                    if (checksum.equals(localChecksum)) {
                        // Server file and local file are identical: everything up to date
                        actions.add(UpdateAction.createUpToDateAction(filename));
                    } else {
                        // Local file present but not up to date
                        actions.add(UpdateAction.createUpdateAction(filename));
                    }
                } else {
                    // The file is not in the local Manifest: download it.
                    actions.add(UpdateAction.createDownloadAction(filename));
                }
            }

        }

        // Replace the manifest file
        IO_Utils.downloadAndSaveFile(IO_Utils.DATA_FOLDER, LOCAL_MF, REMOTE_MF, context);

        return actions;
    }

    private static String checksumOfFile(List<String> list, String filename) {
        for(String info : list) {
            String[] parts = info.split(":");
            if(parts[0].equals(filename)) {
                return parts[1];
            }
        }
        return "";
    }

    private static boolean fileListContains(List<String> list, String filename) {
        for(String info : list) {
            String[] parts = info.split(":");
            if(parts[0].equals(filename)) {
                return true;
            }
        }
        return false;
    }

    public static class UpdateAction {

        private static final int TYPE_UP_TO_DATE = 0;
        private static final int TYPE_DOWNLOAD = 1;
        private static final int TYPE_UPDATE = 2;

        private final int type;
        private final String filename;

        private UpdateAction(int type, String filename) {
            this.type = type;
            this.filename = filename;
        }

        public String getActionText() {
            switch (type) {
                case TYPE_UP_TO_DATE:
                    return filename+" is up to date";
                case TYPE_DOWNLOAD:
                    return filename+" needs to be downloaded.";
                case TYPE_UPDATE:
                    return filename+" needs to be updated.";
            }

            return "Unknown action type";
        }

        public void processAction(Context context) throws Exception {
            switch (type) {
                case TYPE_DOWNLOAD:
                case TYPE_UPDATE:
                    String[] pathParts = filename.split("/");
                    String folder = IO_Utils.DATA_FOLDER;
                    if(pathParts.length>1) {
                        for(int i=0;i<(pathParts.length-1);i++) {
                            folder += "_" + pathParts[i];
                        }
                    }
                    String localFilename =  pathParts[pathParts.length-1];
                    if(localFilename.endsWith("jpg") || localFilename.endsWith("jpeg")) {
                        IO_Utils.downloadImage(IO_Utils.DOWNLOAD_BASE_URL+filename, folder, localFilename, context);
                    }
                    else {
                        IO_Utils.downloadAndSaveFile(folder, localFilename, IO_Utils.DOWNLOAD_BASE_URL+filename, context);
                    }
                    break;
            }
        }

        public static UpdateAction createUpdateAction(String filename) {
            return new UpdateAction(TYPE_UPDATE, filename);
        }

        public static UpdateAction createDownloadAction(String filename) {
            return new UpdateAction(TYPE_DOWNLOAD, filename);
        }

        public static UpdateAction createUpToDateAction(String filename) {
            return new UpdateAction(TYPE_UP_TO_DATE, filename);
        }
    }
}
