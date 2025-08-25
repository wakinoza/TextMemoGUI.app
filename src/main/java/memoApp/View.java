package memoApp;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**.
 * UIを司るクラス
 */
public class View {
  /**.メモと保存ボタンのパネル */
  private final JPanel MEMO_PANEL;

  /**.メモを記述するテキストパネル */
  private final JTextArea MEMO_TEXT_AREA;

  /**.過去の保存履歴を表示するパネル*/
  private JScrollPane historyScrollPane;

  /**.テキスト保存ボタン*/
  private final JButton SAVE_BUTTON;

  /**.テキスト削除ボタン*/
  private final JButton TEXT_CLEAR_BUTTON;

  /**.再編集ボタン*/
  private JButton editButton;

  /**.履歴削除ボタン */
  private JButton clearHistoryButton;

  /**.
   * コンストラクタ
   */
  public View() {
    MEMO_PANEL = new JPanel(new BorderLayout());
    MEMO_TEXT_AREA = new JTextArea();
    SAVE_BUTTON = new JButton("保存");
    TEXT_CLEAR_BUTTON = new JButton("削除");

    JScrollPane textScrollPane = new JScrollPane(MEMO_TEXT_AREA);
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    buttonPanel.add(SAVE_BUTTON);
    buttonPanel.add(TEXT_CLEAR_BUTTON);

    MEMO_PANEL.add(textScrollPane, BorderLayout.CENTER);
    MEMO_PANEL.add(buttonPanel, BorderLayout.SOUTH);

    historyScrollPane = new JScrollPane();
  }

  /**.
   * MEMO_TEXT_AREAのgetterメソッド
   *
   * @return MEMO_TEXT_AREAへの参照
   */
  public JTextArea getMemoTextArea() {
    return this.MEMO_TEXT_AREA;
  }

  /**.
   * MEMO_PANELのgetterメソッド
   *
   * @return MEMO_PANELへの参照
   */
  public JPanel getMemoPanel() {
    return this.MEMO_PANEL;
  }

  /**.
   * SAVE＿BUTTONのgetterメソッド
   *
   * @return SAVE_BUTTONへの参照
   */
  public JButton getSaveButton() {
    return this.SAVE_BUTTON;
  }

  /**.
   * TEXT_CLEAR_BUTTONのgetterメソッド
   *
   * @return TEXT_CLEAR_BUTTONへの参照
   */
  public JButton getTextClearButton() {
    return this.TEXT_CLEAR_BUTTON;
  }

  /**.
   * editButtonのgetterメソッド
   *
   * @return editButtonへの参照
   */
  public JButton getEditButton() {
    return this.editButton;
  }

  /**.
   * clearHistoryButtonのgetterメソッド
   *
   * @return clearHistoryButtonへの参照
   */
  public JButton getClearHistoryButton() {
    return this.clearHistoryButton;
  }

  /**.
   * historyScrollPaneのgetterメソッド
   *
   * @return historyScrollPaneへの参照
   */
  public JScrollPane getHistoryScrollPane() {
    return this.historyScrollPane;
  }

  /**.
   * メモ欄に引数で指定されたテキストを表示するメソッド
   *
   * @param content 新しく表示したいテキスト
   */
  public void setMemoContent(String content) {
    MEMO_TEXT_AREA.setText(content);
  }

  /**.
   * 履歴のUIを作成するメソッド
   *
   * @param fileNames 保存されているメモのファイル名のリスト
   */
  public void updateHistoryPanel(List<String> fileNames, Control control) {
    historyScrollPane.removeAll();

    JPanel historyContainerPanel = new JPanel();
    historyContainerPanel.setLayout(new BoxLayout(historyContainerPanel, BoxLayout.Y_AXIS));

    historyScrollPane = new JScrollPane(historyContainerPanel);

    for (String fileName: fileNames) {
      JPanel historyPanel = new JPanel();
      historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.X_AXIS));

      JLabel fileNameLabel = new JLabel(fileName);
      editButton = new JButton("再編集");
      editButton.setActionCommand(fileName);
      control.setupEditListener(editButton);
      clearHistoryButton = new JButton("履歴削除");
      clearHistoryButton.setActionCommand(fileName);
      control.setupClearHistoryListener(clearHistoryButton);

      historyPanel.add(fileNameLabel);
      historyPanel.add(editButton);
      historyPanel.add(clearHistoryButton);

      historyContainerPanel.add(historyPanel);

    }

    historyScrollPane.revalidate();
    historyScrollPane.repaint();
  }
}
