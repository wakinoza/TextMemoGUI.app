package memoApp;

import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

/**.
 * ModelクラスとViewクラスのかけ渡しを担うクラス
 */
public class Control {
  /**.Modelクラスのインスタンスへの参照*/
  private final Model MODEL;

  /**.Viewクラスのインスタンスへの参照*/
  private final View VIEW;

  /**.
   * コンストラクタ
   *
   * @param model Modelクラスのインスタンスへの参照
   * @param view Viewクラスのインスタンスへの参照
   */
  public Control(Model model, View view) {
    this.MODEL = model;
    this.VIEW = view;
  }

  /**.
   * ボタン操作の処理を指示するメソッド
   */
  public void setupListeners() {

    //保存ボタン
    VIEW.getSaveButton().addActionListener(e -> {
      String content = VIEW.getMemoTextArea().getText();

      if (content.isEmpty()) {
        JOptionPane.showMessageDialog(null, "メモが空です。", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      try {
        MODEL.saveMemo(content);
        JOptionPane.showMessageDialog(null, "メモが保存されました。", "information",
            JOptionPane.INFORMATION_MESSAGE);
        VIEW.getMemoTextArea().setText("");
        updateHistoryPanel(); // リストを更新するメソッドを呼び出す
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "ファイルの保存に失敗しました。", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    });

    //テキスト削除ボタン
    VIEW.getTextClearButton().addActionListener(e -> VIEW.getMemoTextArea().setText(""));

    //再編集ボタン
    VIEW.getEditButton().addActionListener(e -> {
      String fileName = e.getActionCommand();

      try {
         String content = MODEL.loadMemoContent(fileName);
         MODEL.clearThisHistory(fileName);
         VIEW.setMemoContent(content);
      } catch (IOException ex) {
         JOptionPane.showMessageDialog(null, "ファイルの読み込みに失敗しました。", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });

    //履歴削除ボタン
    VIEW.getClearHistoryButton() .addActionListener(e -> {
      String fileName = e.getActionCommand();
      try {
        MODEL.clearThisHistory(fileName);
        updateHistoryPanel();
        JOptionPane.showMessageDialog(null, "履歴を消去しました。", "Information", JOptionPane.INFORMATION_MESSAGE);
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "ファイルの削除に失敗しました。", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });
  }

  /**.
   * 履歴表示の再構成を指示するメソッド
   */
  public void updateHistoryPanel() {
    List<String> fileNames = MODEL.getHistoryList();
    VIEW.updateHistoryPanel(fileNames);
  }
}
