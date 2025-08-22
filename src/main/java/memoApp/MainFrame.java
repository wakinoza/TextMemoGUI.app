package memoApp;

import javax.swing.JFrame;
import java.awt.BorderLayout;

/**.
 * GUI表示を司るクラス
 * JFrameの設定、必要なレイアウトマネージャやパネルの設置、コントローラークラスにリスナーを設定する
 */
public class MainFrame extends JFrame {

  /**
   * .
   * コンストラクタ
   *
   * @param view    Viewクラスのインスタンスへの参照
   * @param control Controlクラスのインスタンスへの参照
   */
  public MainFrame(View view, Control control) {
    setTitle("シンプルなメモアプリ");
    setSize(1200, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    control.updateHistoryPanel();

    setLayout(new BorderLayout());
    add(view.getMemoPanel(), BorderLayout.CENTER);
    add(view.getHistoryScrollPane(), BorderLayout.WEST);

    control.setupListeners();
  }
}
