package memoApp;

import javax.swing.SwingUtilities;

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
