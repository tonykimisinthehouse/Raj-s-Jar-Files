package edu.gatech.cs2340.rajsjarfiles.spacetrader.model;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class used to save game models.
 */
public class ModelSaver {
    public static final String DEFAULT_MODEL_FILE_NAME = "model.bin";

    /**
     * Saves the singleton model to a given file path. The file path is a
     * parameter because saving requires knowing the package context.
     *
     * @param filePath the file path
     * @return whether or not the model is saved successfully
     */
    public static boolean saveBinaryModel(String filePath) {
        boolean success = true;
        try {
            Log.d("ModelSaver", filePath);
            File file = new File(filePath + DEFAULT_MODEL_FILE_NAME);

            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            out.writeObject(Model.current);
            out.close();
        } catch (IOException e) {
            Log.e("ModelSaver", "Error writing an entry from binary file", e);
            success = false;
        }
        return success;
    }

    /**
     * Loads the singleton model from a given file path. The file path is a
     * parameter because saving requires knowing the package context.
     *
     * @param filePath the file path
     * @return whether or not the model is loaded successfully
     */
    public static Model loadBinaryModel(String filePath) {
        Model model = null;

        try {
            Log.d("ModelSaver", "loading from: " + filePath);
            File file = new File(filePath + DEFAULT_MODEL_FILE_NAME);

            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            model = (Model) in.readObject();
            Model.current = model;
            in.close();
        } catch (IOException e) {
            Log.e("ModelSaver", "Error reading an entry from binary file", e);
        } catch (ClassNotFoundException e) {
            Log.e("ModelSaver",
                    "Error casting a class from the binary file", e);
        }
        return model;
    }
}
