package com.whatshroom;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Pair;

import org.tensorflow.lite.Interpreter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class ShroomsClassifier {

    private int _pixelNum = 64;
    private Context _context;

    public ShroomsClassifier(Context context) {
        _context = context;
    }

    public Pair<String, Float> predict(Bitmap bitmap) {
        MappedByteBuffer modelFile = null;
        List<String> labels = null;
        try {
            modelFile = loadModelFile(_context.getAssets(), "model.tflite");
            labels = loadLabelList(_context.getAssets(), "labels.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (modelFile == null || labels == null)
            return null;

        float[][][][] input = preprocessInput(bitmap);
        float[][] output = new float[1][labels.size()];
        Interpreter interpreter = new Interpreter(modelFile);
        interpreter.run(input, output);
        interpreter.close();

        float maxProbability = 0;
        int predictedIndex = -1;
        for (int i=0; i<output[0].length; i++) {
            if (output[0][i] > maxProbability) {
                maxProbability = output[0][i];
                predictedIndex = i;
            }
        }
        String predictedLabel = labels.get(predictedIndex);
        return new Pair<>(predictedLabel, maxProbability);
    }

    private float[][][][] preprocessInput(Bitmap bitmap) {
        bitmap = Bitmap.createScaledBitmap(bitmap, _pixelNum, _pixelNum, true);
        float[][][][] input = new float[1][_pixelNum][_pixelNum][3];
        for (int x=0; x<_pixelNum; x++) {
            for (int y=0; y<_pixelNum; y++) {
                int pixel = bitmap.getPixel(x, y);
                input[0][x][y][0] = Color.blue(pixel) / 255.0f;
                input[0][x][y][1] = Color.green(pixel) / 255.0f;
                input[0][x][y][2] = Color.red(pixel) / 255.0f;
            }
        }
        return input;
    }

    private MappedByteBuffer loadModelFile(AssetManager assetManager, String modelPath) throws IOException {
        AssetFileDescriptor fileDescriptor = assetManager.openFd(modelPath);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    private List<String> loadLabelList(AssetManager assetManager, String labelPath) throws IOException {
        List<String> labelList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(assetManager.open(labelPath)));
        String line;
        while ((line = reader.readLine()) != null) {
            labelList.add(line);
        }
        reader.close();
        return labelList;
    }
}
