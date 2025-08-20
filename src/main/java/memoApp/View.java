package memoApp;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.BorderFactory;
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
  /**.
   * メモと保存ボタンのパネル
   */
  private JPanel memoPanel;

  /**.
   * 過去の保存履歴を表示するパネル
   */
  private JScrollPane historyScrollPane;

  /**.
   * メモを記述するテキストパネル
   */
  private JTextArea memoTextArea;

  /**.
   * コンストラクタ
   */
  public View() {
    memoPanel = new JPanel(new BorderLayout());
    memoTextArea = new JTextArea();
    JButton saveButton = new JButton("保存");

    JScrollPane textScrollPane = new JScrollPane(memoTextArea);
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(saveButton);

    memoPanel.add(textScrollPane, BorderLayout.CENTER);
    memoPanel.add(buttonPanel, BorderLayout.SOUTH);

    JScrollPane historyScrollPane = new JScrollPane();
  }

  /**.
   * memoTextAreaのgetterメソッド
   * @return memoTextAreaへの参照
   */
  public JTextArea getMemoTextArea() {
    return memoTextArea;
  }

  /**.
   * MemoPanelのgetterメソッド
   * @return memoPanelへの参照
   */
  public JPanel getMemoPanel() {
    return memoPanel;
  }

  /**.
   * historyScrollPaneのgetterメソッド
   * @return historyScrollPaneへの参照
   */
  public JScrollPane getHistoryScrollPane() {
    return historyScrollPane;
  }

  /**.
   * メモ欄に引数で指定されたテキストを表示するメソッド
   * @param content 新しく表示したいテキスト
   */
  public void setMemoContent(String content) {
    memoTextArea.setText(content);
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
