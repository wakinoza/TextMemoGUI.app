package memoApp;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {
  public MainFrame(MemoView view, MemoController controller) {
    setTitle("シンプルなメモアプリ");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // レイアウトマネージャを設定
    setLayout(new BorderLayout());

    // ビューをウィンドウに追加
    add(view.getPanel(), BorderLayout.CENTER);

    // コントローラーにリスナーを設定
    controller.setupListeners();
  }
}
