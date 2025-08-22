package memoApp;

import javax.swing.SwingUtilities;

/**.
 * メインクラス
 * イベントディスパッチスレッドでUIを生成すし、必要なクラスのインスタンスを生成する
 */
public class Main {
  public static void main(String[] args) {
      SwingUtilities.invokeLater(() -> {
      Model model = new Model();
      View view = new View();
      Control control = new Control(model, view);
      MainFrame frame = new MainFrame(view, control);
      frame.setVisible(true);
    });
  }
}
