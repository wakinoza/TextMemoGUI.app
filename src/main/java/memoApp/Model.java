package memoApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Model {
  private static final String SAVE_DIR = "src/main/resources/memos/";

  public void saveMemo(String content) throws IOException {
    String fileName = System.currentTimeMillis() + ".txt";
    try (FileWriter writer = new FileWriter(fileName)) {
      writer.write(content);
    }
  }

  public List<String> getHistoryList() {
    List<String> fileNames = new ArrayList<>();
    File dir = new File(SAVE_DIR);
    File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
    if (files != null) {
      for (File file : files) {
        fileNames.add(file.getName());
      }
    }
    return fileNames;
  }

  public String loadMemoContent(String fileName) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(SAVE_DIR + fileName));
    return new String(bytes);
  }

  public void clearThisHistory(String fileName) throws IOException{
    Files.delete(Paths.get(SAVE_DIR + fileName));
  }
}
