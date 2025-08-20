package memoApp;

import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * GUI表示を司るクラス
 * Jframeの設定、必要なレイアウトマネージャやパネルの設置、コントローラークラスにリスナーを設定する
 */
public class MainFrame extends JFrame {
  public MainFrame(MemoView view, MemoController controller) {
    setTitle("シンプルなメモアプリ");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(new BorderLayout());

    add(view.getPanel(), BorderLayout.CENTER);

    controller.setupListeners();
  }
}
