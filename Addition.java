import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Addition extends Applet implements ActionListener {
  private TextField[] carryFields = new TextField[7];
  private TextField[] topFields = new TextField[8];
  private TextField[] bottomFields = new TextField[8];
  private TextField[] binaryAnswerFields = new TextField[8];
  private TextField[] enterDecimalFields = new TextField[2];
  private TextField[] enterBinaryFields = new TextField[2];
  private TextField[] answerFields = new TextField[2];
  private Label decimalLabel = new Label("Base 10");
  private Label binaryLabel = new Label("Binary");
  private Button addButton = new Button("ADD!");
  private Button resetButton = new Button("RESET");
  private Font carryFont = new Font("Arial Black", Font.BOLD, 16);
  private Font additionFont = new Font("Wide Latin", Font.BOLD, 40);
  private Font enterFont = new Font("Cooper Std Black", Font.PLAIN, 26);

  public void init() {
    setSize(900,450);

    for (int i = 0; i < carryFields.length; i++) {
      carryFields[i] = new TextField(1);
      carryFields[i].setFont(carryFont);
      carryFields[i].setEditable(false);
      add(carryFields[i]);
    }
    for (int i = 0; i < topFields.length; i++) {
      topFields[i] = new TextField(1);
      topFields[i].setFont(additionFont);
      topFields[i].setEditable(false);
      add(topFields[i]);
    }
    for (int i = 0; i < bottomFields.length; i++) {
      bottomFields[i] = new TextField(1);
      bottomFields[i].setFont(additionFont);
      bottomFields[i].setEditable(false);
      add(bottomFields[i]);
    }
    for (int i = 0; i < binaryAnswerFields.length; i++) {
      binaryAnswerFields[i] = new TextField(1);
      binaryAnswerFields[i].setFont(additionFont);
      binaryAnswerFields[i].setEditable(false);
      add(binaryAnswerFields[i]);
    }
    for(int i = 0; i < enterDecimalFields.length; i++) {
      enterDecimalFields[i] = new TextField(1);
      enterDecimalFields[i].setFont(enterFont);
      add(enterDecimalFields[i]);
    }
    for (int i = 0; i < enterBinaryFields.length; i++) {
      enterBinaryFields[i] = new TextField(1);
      enterBinaryFields[i].setFont(enterFont);
      add(enterBinaryFields[i]);
    }
    for(int i = 0; i < answerFields.length; i++) {
      answerFields[i] = new TextField(1);
      answerFields[i].setFont(enterFont);
      answerFields[i].setEditable(false);
      add(answerFields[i]);
    }

    addButton.addActionListener(this);
    resetButton.addActionListener(this);

    add(decimalLabel);
    add(binaryLabel);
    add(addButton);
    add(resetButton);
  }

  public void paint(Graphics g) {
    g.drawLine(100,300,550,300);
    g.drawLine(110,240,110,280);
    g.drawLine(90,260,130,260);

    int xCounter = 150;
    for (int i = 0; i < binaryAnswerFields.length; i++) {
      if (i < 7) {
        carryFields[i].setBounds(xCounter + 10, 120, 30, 30);
      }
      topFields[i].setBounds(xCounter, 170, 50, 50);
      bottomFields[i].setBounds(xCounter, 240, 50, 50);
      binaryAnswerFields[i].setBounds(xCounter, 320, 50, 50);
      xCounter += 50;
    }

    g.drawLine(600, 300, 870, 300);
    g.drawLine(600, 240, 600, 280);
    g.drawLine(580, 260, 620, 260);

    binaryLabel.setBounds(650, 120, 50, 20);
    decimalLabel.setBounds(780, 120, 50, 20);
    enterBinaryFields[0].setBounds(630, 170, 110, 40);
    enterBinaryFields[1].setBounds(630, 240, 110, 40);
    enterDecimalFields[0].setBounds(770, 170, 110, 40);
    enterDecimalFields[1].setBounds(770, 240, 110, 40);
    answerFields[0].setBounds(630, 320, 110, 40);
    answerFields[1].setBounds(770, 320, 110, 40);
    addButton.setBounds(650, 30, 80, 50);
    resetButton.setBounds(790, 30, 80, 50);
  }

  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (source == addButton) {
      int topNum = 0;
      int bottomNum = 0;
      String topBinary = "";
      String bottomBinary = "";
      int b = 0;
      if (enterBinaryFields[0].getText().length() != 0) {
        try {
          topNum = Integer.parseInt(enterBinaryFields[0].getText(), 2);
          enterDecimalFields[0].setText("" + topNum);
          b++;
        }
        catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(null, "You must enter in a binary number","Invalid Entry", JOptionPane.ERROR_MESSAGE);
          enterBinaryFields[0].requestFocusInWindow();
          b = 0;
        }
      }
      else if (enterDecimalFields[0].getText().length() != 0) {
        try {
          topNum = Integer.parseInt(enterDecimalFields[0].getText());
          enterBinaryFields[0].setText(Integer.toBinaryString(topNum));
          b++;
        }
        catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(null, "You must enter in a number", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
          enterDecimalFields[0].requestFocusInWindow();
          b = 0;
        }
      }
      else topNum = 0;

      if (enterBinaryFields[1].getText().length() != 0) {
        try {
          bottomNum = Integer.parseInt(enterBinaryFields[1].getText(), 2);
          enterDecimalFields[1].setText("" + bottomNum);
          b++;
        }
        catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(null, "You must enter in a binary number", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
          enterBinaryFields[1].requestFocusInWindow();
          b = 0;
        }
      }
      else if (enterDecimalFields[1].getText().length() != 0) {
        try {
          bottomNum = Integer.parseInt(enterDecimalFields[1].getText());
          enterBinaryFields[1].setText(Integer.toBinaryString(bottomNum));
          b++;
        }
        catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(null, "You must enter in a number", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
          enterDecimalFields[1].requestFocusInWindow();
          b = 0;
        }
      }
      else bottomNum = 0;

      if (topNum + bottomNum > 0xff) {
        JOptionPane.showMessageDialog(null, "The sum is too large to show", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
        return;
      }

      if (b == 2) {
        topBinary = Integer.toBinaryString(topNum);
        bottomBinary = Integer.toBinaryString(bottomNum);
        for (int i = 0; i < topFields.length; i++) {
          topFields[i].setText("");
          bottomFields[i].setText("");
        }
        for (int i = 0; i < topFields.length; i++) {
          if (i < topBinary.length()) {
            topFields[7 - i].setText("" + topBinary.charAt(topBinary.length() - 1 - i));
          }
          if (i < bottomBinary.length()) {
            bottomFields[7 - i].setText("" + bottomBinary.charAt(bottomBinary.length() - 1 - i));
          }
        }
        answerFields[0].setText(Integer.toBinaryString(topNum + bottomNum));
        answerFields[1].setText("" + (topNum + bottomNum));

        int carry = 0;
        for (int i = 7; i >= 0; i--) {
          if (topFields[i].getText().length() == 0 && bottomFields[i].getText().length() == 0 && carryFields[i].getText().length() == 0) {
            break;
          }
          pause();
          topFields[i].setBackground(Color.RED);
          pause();
          bottomFields[i].setBackground(Color.YELLOW);
          pause();
          binaryAnswerFields[i].setBackground(Color.GREEN);
          int result = -1;
          if (topFields[i].getText().length() == 0 && bottomFields[i].getText().length() != 0) {
            result = Integer.parseInt(bottomFields[i].getText()) + carry;
          }
          else if (topFields[i].getText().length() != 0 && bottomFields[i].getText().length() == 0) {
            result = Integer.parseInt(topFields[i].getText()) + carry;
          }
          else if (topFields[i].getText().length() == 0 && bottomFields[i].getText().length() == 0 && carry != 0) {
            result = carry;
          }
          else if (topFields[i].getText().length() != 0 && bottomFields[i].getText().length() != 0) {
            result = Integer.parseInt(topFields[i].getText()) + Integer.parseInt(bottomFields[i].getText()) + carry;
          }

          if (result < 2) {
            binaryAnswerFields[i].setText("" + result);
            carry = 0;
          } else if (result == 2) {
            binaryAnswerFields[i].setText("0");
            carry = 1;
            pause();
            carryFields[i - 1].setBackground(Color.ORANGE);
            carryFields[i - 1].setText("1");
          } else {
            binaryAnswerFields[i].setText("1");
            carry = 1;
            pause();
            carryFields[i - 1].setBackground(Color.ORANGE);
            carryFields[i - 1].setText("1");
          }
        }
        b = 0;
      }
    }

    if (source == resetButton) {
      for (int i = 0; i < binaryAnswerFields.length; i++) {
        binaryAnswerFields[i].setText("");
        binaryAnswerFields[i].setBackground(SystemColor.control);
        topFields[i].setText("");
        topFields[i].setBackground(SystemColor.control);
        bottomFields[i].setText("");
        bottomFields[i].setBackground(SystemColor.control);
        if (i < 7) {
          carryFields[i].setText("");
          carryFields[i].setBackground(SystemColor.control);
        }
        if(i < 2) {
          enterBinaryFields[i].setText("");
          enterDecimalFields[i].setText("");
          answerFields[i].setText("");
        }
      }
    }

  }

  public void pause() {
    try {
      Thread.sleep(350);
    }
    catch(InterruptedException ie) {
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Unsigned Binary Addition");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    Applet thisApplet = new Addition();
    frame.getContentPane().add(thisApplet, BorderLayout.CENTER);
    thisApplet.init();
    frame.setSize(thisApplet.getSize());
    thisApplet.start();
    frame.setVisible(true);
  }
}
