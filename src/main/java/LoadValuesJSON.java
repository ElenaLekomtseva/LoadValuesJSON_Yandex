import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class LoadValuesJSON {

    /**
     * Load values in main JSON from second JSON. The result is written to the new JSON.
     * @param mainFileReader
     * @param secondFileReader
     * @param resultFileWriter
     */
    public static void loadValues(FileReader mainFileReader, FileReader secondFileReader, FileWriter resultFileWriter) {
        Gson gson = new GsonBuilder().create();

        Type token = new TypeToken<HashMap<String, Object>>() {
        }.getType();
        Map<String, Object> mainJson = gson.fromJson(mainFileReader, token);
        Map<String, Object> secondJson = gson.fromJson(secondFileReader, token);

        for (Map.Entry element : secondJson.entrySet()) {
            mainJson.put(element.getKey().toString(), element.getValue());
        }

        gson.toJson(mainJson, resultFileWriter);
    }

    public static void main(String[] args) {

        try (FileReader mainFileReader = new FileReader(LoadValuesJSON.class
                .getClassLoader().getResource("main.json").getFile());

             FileReader secondFileReader = new FileReader(LoadValuesJSON.class
                     .getClassLoader().getResource("second.json").getFile());

             FileWriter resultFileWriter = new FileWriter(new File("result.json"))) {

            loadValues(mainFileReader, secondFileReader, resultFileWriter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
