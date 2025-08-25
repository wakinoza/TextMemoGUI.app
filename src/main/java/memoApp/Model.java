package memoApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**.
 * データの読み書きを行うクラス
 */
public class Model {
  /**.テキストファイルを保存するディレクトリのパス*/
  private static final String SAVE_DIR = "memos/";

  /**.
   * コンストラクタ
   */
  public Model() {
    File dir = new File(SAVE_DIR);
    if (!dir.exists()) {
      dir.mkdir();
    }
  }

  /**テキストエリアの文字列データを、テキストファイルに書き込むメソッド.
   *
   * @param content テキストエリアの文字列データ
   * @throws IOException
   */
  public void saveMemo(String content) throws IOException {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    LocalDateTime now = LocalDateTime.now();
    String fileName = formatter.format(now) + ".txt";
    try (FileWriter writer = new FileWriter(SAVE_DIR + fileName)) {
      writer.write(content);
    }
  }

  /**.
   * テキストファイルのファイル名の一覧を取得するメソッド
   *
   * @return ファイル名の文字列情報のList
   */
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

  /**.
   * 指定されたファイル名の文字列データを読み出すメソッド
   *
   * @param fileName 指定するファイル名
   * @return  指定するファイルの文字列データ
   * @throws IOException
   */
  public String loadMemoContent(String fileName) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(SAVE_DIR + fileName));
    return new String(bytes);
  }

  /**.
   * 指定されたテキストファイルを削除するメソッド
   *
   * @param fileName 削除したいファイル名
   * @throws IOException
   */
  public void clearThisHistory(String fileName) throws IOException{
    Files.delete(Paths.get(SAVE_DIR + fileName));
  }
}
