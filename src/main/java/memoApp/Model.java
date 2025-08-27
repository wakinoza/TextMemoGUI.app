package memoApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
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

  /**テキストエリアの文字列データを、テキストファイルに書き込むメソッド.
   *
   * @param content テキストエリアの文字列データ
   * @throws IOException 指定されたファイルが存在するが通常ファイルではなくディレクトリである場合、存在せず作成もできない場合、またはなんらかの理由で開くことができない場合
   */
  public void saveMemo(String content) throws IOException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    LocalDateTime now = LocalDateTime.now();
    try (Writer output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Main.getSaveDir() + formatter.format(now) + ".txt"), StandardCharsets.UTF_8))) {
      output.write(content);
    }
  }

  /**.
   * テキストファイルのファイル名の一覧を取得するメソッド
   *
   * @return ファイル名の文字列情報のList
   */
  public List<String> getHistoryList() {
    List<String> fileNames = new ArrayList<>();
    File dir = new File(Main.getSaveDir());
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
   * @throws IOException ファイルからの読取り中に入出力エラーが発生した場合、または形式が間違っているか、マップできないバイト・シーケンスが読み取られた場合
   */
  public String loadMemoContent(String fileName) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(Main.getSaveDir() + fileName));
    return new String(bytes, StandardCharsets.UTF_8);
  }

  /**.
   * 指定されたテキストファイルを削除するメソッド
   *
   * @param fileName 削除したいファイル名
   * @throws IOException 入出力エラーが発生した場合
   */
  public void clearThisHistory(String fileName) throws IOException {
    Files.delete(Paths.get(Main.getSaveDir() + fileName));
  }
}
