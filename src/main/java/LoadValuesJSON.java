import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class LoadValuesJSON {

    /**
     * Load values in main JSON from second JSON. The result is written to the new result.json
     *
     * @param mainFile
     * @param secondFile
     */
    static void loadValues(String mainFile, String secondFile) {

        try (FileReader mainFileReader = new FileReader(mainFile);
             FileReader secondFileReader = new FileReader(secondFile);
             FileWriter resultFileWriter = new FileWriter(new File(new File(mainFile).getParent() + "/result.json"))) {

            Gson gson = new GsonBuilder().create();

            Type token = new TypeToken<HashMap<String, Object>>() {}.getType();
            Map<String, Object> mainJson = gson.fromJson(mainFileReader, token);
            Map<String, Object> secondJson = gson.fromJson(secondFileReader, token);

            for (Map.Entry element : secondJson.entrySet()) {
                mainJson.put(element.getKey().toString(), element.getValue());
            }

            gson.toJson(mainJson, resultFileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loadValues(
                "D:\\Work\\EPAM\\QA\\Project\\LoadValuesJSON_Yandex\\src\\main\\resources\\main.json",
                "D:\\Work\\EPAM\\QA\\Project\\LoadValuesJSON_Yandex\\src\\main\\resources\\second.json");
    }
}

