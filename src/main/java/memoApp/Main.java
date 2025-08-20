package memoApp;

import javax.swing.SwingUtilities;

/**
 * メインメソッド
 * イベントディスパッチスレッドでUIを生成すし、必要なクラスのインスタンスを生成する
 */
public class Main {
  public static void main(String[] args) {
      SwingUtilities.invokeLater(() -> {
      MemoModel model = new MemoModel();
      MemoView view = new MemoView();
      MemoController controller = new MemoController(model, view);
      MainFrame frame = new MainFrame(view, controller);
      frame.setVisible(true);
    });
  }
}
