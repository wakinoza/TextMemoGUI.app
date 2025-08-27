package memoApp;

import java.awt.BorderLayout;
import java.awt.Color;
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
  /**. メモと保存ボタンのパネル */
  private final JPanel MEMO_PANEL;

  /**. メモを記述するテキストパネル */
  private final JTextArea MEMO_TEXT_AREA;

  /**. 過去の保存履歴を表示するパネル*/
  private final JScrollPane HISTORY_SCROLL_PANE;

  /**. テキスト保存ボタン*/
  private final JButton SAVE_BUTTON;

  /**. テキスト削除ボタン*/
  private final JButton TEXT_CLEAR_BUTTON;

  /**.
   * コンストラクタ
   */
  public View() {
    MEMO_PANEL = new JPanel(new BorderLayout());
    MEMO_TEXT_AREA = new JTextArea();
    SAVE_BUTTON = new JButton("保存");
    SAVE_BUTTON.setBackground(Color.GREEN);
    TEXT_CLEAR_BUTTON = new JButton("削除");
    TEXT_CLEAR_BUTTON.setForeground(Color.RED);

    JScrollPane textScrollPane = new JScrollPane(MEMO_TEXT_AREA);
    MEMO_PANEL.add(textScrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

    buttonPanel.add(SAVE_BUTTON);
    buttonPanel.add(TEXT_CLEAR_BUTTON);
    MEMO_PANEL.add(buttonPanel, BorderLayout.SOUTH);

    HISTORY_SCROLL_PANE = new JScrollPane();
  }

  /**.
   * MEMO_TEXT_AREAのgetterメソッド
   *
   * @return MEMO_TEXT_AREAへの参照
   */
  @SuppressWarnings("EI_EXPOSE_REP")
  public JTextArea getMemoTextArea() {
    return this.MEMO_TEXT_AREA;
  }

  /**.
   * MEMO_PANELのgetterメソッド
   *
   * @return MEMO_PANELへの参照
   */
  @SuppressWarnings("EI_EXPOSE_REP")
  public JPanel getMemoPanel() {
    return this.MEMO_PANEL;
  }

  /**.
   * SAVE＿BUTTONのgetterメソッド
   *
   * @return SAVE_BUTTONへの参照
   */
  @SuppressWarnings("EI_EXPOSE_REP")
  public JButton getSaveButton() {
    return this.SAVE_BUTTON;
  }

  /**.
   * TEXT_CLEAR_BUTTONのgetterメソッド
   *
   * @return TEXT_CLEAR_BUTTONへの参照
   */
  @SuppressWarnings("EI_EXPOSE_REP")
  public JButton getTextClearButton() {
    return this.TEXT_CLEAR_BUTTON;
  }

  /**.
   * historyScrollPaneのgetterメソッド
   *
   * @return historyScrollPaneへの参照
   */@SuppressWarnings("EI_EXPOSE_REP")
  public JScrollPane getHistoryScrollPane() {
    return this.HISTORY_SCROLL_PANE;
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

    JPanel historyContainerPanel = new JPanel();
    historyContainerPanel.setLayout(new BoxLayout(historyContainerPanel, BoxLayout.Y_AXIS));

    for (String fileName : fileNames) {
      JPanel historyPanel = new JPanel();
      historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.X_AXIS));

      JLabel fileNameLabel = new JLabel(fileName);
      historyPanel.add(fileNameLabel);

      JButton editButton = new JButton("再編集");
      editButton.setActionCommand(fileName);
      control.setupEditListener(editButton);

      JButton clearHistoryButton = new JButton("履歴削除");
      clearHistoryButton.setActionCommand(fileName);
      clearHistoryButton.setForeground(Color.RED);
      control.setupClearHistoryListener(clearHistoryButton);

      historyPanel.add(editButton);
      historyPanel.add(clearHistoryButton);

      historyContainerPanel.add(historyPanel);

    }
    HISTORY_SCROLL_PANE.setViewportView(historyContainerPanel);

    HISTORY_SCROLL_PANE.revalidate();
    HISTORY_SCROLL_PANE.repaint();
  }
}
