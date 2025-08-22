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

  /**.
   * コンストラクタ
   */
  public View() {
    MEMO_PANEL = new JPanel(new BorderLayout());
    MEMO_TEXT_AREA = new JTextArea();
    JButton saveButton = new JButton("保存");

    JScrollPane textScrollPane = new JScrollPane(MEMO_TEXT_AREA);
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(saveButton);

    MEMO_PANEL.add(textScrollPane, BorderLayout.CENTER);
    MEMO_PANEL.add(buttonPanel, BorderLayout.SOUTH);

    historyScrollPane = new JScrollPane();
  }

  /**.
   * memoTextAreaのgetterメソッド
   *
   * @return memoTextAreaへの参照
   */
  public JTextArea getMemoTextArea() {
    return this.MEMO_TEXT_AREA;
  }

  /**.
   * MemoPanelのgetterメソッド
   *
   * @return memoPanelへの参照
   */
  public JPanel getMemoPanel() {
    return this.MEMO_PANEL;
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
  public void updateHistoryPanel(List<String> fileNames) {
    historyScrollPane.removeAll();

    JPanel historyContainerPanel = new JPanel();
    historyContainerPanel.setLayout(new BoxLayout(historyContainerPanel, BoxLayout.Y_AXIS));

    historyScrollPane = new JScrollPane(historyContainerPanel);

    for (String fileName: fileNames) {
      JPanel historyPanel = new JPanel();
      historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.X_AXIS));

      JLabel fileNameLabel = new JLabel(fileName);
      JButton editButton = new JButton("再編集");

      historyPanel.add(fileNameLabel);
      historyPanel.add(editButton);

      historyContainerPanel.add(historyPanel);
    }
    historyScrollPane.revalidate();
    historyScrollPane.repaint();
  }
}
