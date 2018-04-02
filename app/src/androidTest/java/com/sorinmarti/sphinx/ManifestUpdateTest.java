package com.sorinmarti.sphinx;

import android.content.Context;
//import android.support.test.InstrumentationRegistry;
//import android.support.test.runner.AndroidJUnit4;

import com.sorinmarti.sphinx.quiz.IO_Utils;
import com.sorinmarti.sphinx.quiz.QuizUpdater;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileOutputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by SOMA on 21.10.2017.
 */
//@RunWith(AndroidJUnit4.class)
public class ManifestUpdateTest {

    /*
    String manifestName = "Manifest.sphinx";

    @Test
    public void checkInternalIOFunctions() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        // Delete a file
        IO_Utils.deleteFile(manifestName, appContext);

        // Check if file does not exist (anymore)
        boolean manifestExists = IO_Utils.fileExists(manifestName, appContext);
        assertEquals(false, manifestExists);

        // Create a file
        IO_Utils.createEmptyFile(manifestName, appContext);

        // Check if file exists (anymore)
        manifestExists = IO_Utils.fileExists(manifestName, appContext);
        assertEquals(true, manifestExists);

        // Check if file is empty
        String fileContents = IO_Utils.getLocalFile(manifestName, appContext);
        assertEquals("", fileContents);

        // Add contents
        IO_Utils.saveFile(manifestName, "HELLO", appContext);

        // Check if file contains new string
        fileContents = IO_Utils.getLocalFile(manifestName, appContext);
        assertEquals("HELLO\n", fileContents);
    }

    @Test
    public void checkFileDownload() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        IO_Utils.downloadAndSaveFile("test.file", "http://www.sorinmarti.com/sphinx/test.file", appContext);

        boolean fileExists = IO_Utils.fileExists("test.file", appContext);
        assertEquals(true, fileExists);

        String fileContents = IO_Utils.getLocalFile("test.file", appContext);
        assertEquals("TEST\n", fileContents);

        IO_Utils.deleteFile("test.file", appContext);

        fileExists = IO_Utils.fileExists("test.file", appContext);
        assertEquals(false, fileExists);
    }

    @Test
    public void checkManifestDownload() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String remoteManifest = "REMOTE_"+manifestName;

        IO_Utils.downloadAndSaveFile(remoteManifest, "http://www.sorinmarti.com/sphinx/getManifest.php", appContext);

        boolean fileExists = IO_Utils.fileExists(remoteManifest, appContext);
        assertEquals(true, fileExists);

        List<String> lineList = IO_Utils.getLocalFileLineList(remoteManifest, appContext);
        assertEquals(2, lineList.size());
    }

    @Test
    public void checkUpdateMechanism() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        deleteAllLocalFiles();

        boolean needsUpdate = QuizUpdater.gameNeedsUpdating(appContext);
        assertEquals(true, needsUpdate);

        List<QuizUpdater.UpdateAction> actions = QuizUpdater.getUpdateActions(appContext);
        assertEquals(2, actions.size());

        for(QuizUpdater.UpdateAction action : actions) {
            action.processAction(appContext);
        }
        assertEquals(true, IO_Utils.fileExists("test.quiz", appContext));
        assertEquals(true, IO_Utils.fileExists("test_002.quiz", appContext));
    }

    private void deleteAllLocalFiles() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        String[] files = IO_Utils.getFileList("", appContext);
        for(String file : files) {
            IO_Utils.deleteFile(file, appContext);
        }
    }
    */
}
