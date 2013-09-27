/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaSpriteSheetMaker;

/**
 *
 * @author Carlo
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class spriteSheetGui extends JFrame implements ActionListener
{
    JPanel mainPanel;
    JPanel upperPanel;
    JPanel lowerPanel;
    JPanel buttonPanel;
    JList listBox;
    JTextField nameField;
    JTextField rowField;
    JTextField columnField;
    JButton addButton;
    JButton removeButton;
    JButton clearButton;
    JButton saveButton;
    JLabel maxWidthLabel;
    JLabel maxHeightLabel;
    DefaultListModel listModel;
    
    // List[] imageList;
    int maxWidth;
    int maxHeight;
    
    public spriteSheetGui()
    {
        super("Sprite Sheet");
        //this.setPreferredSize(new Dimension(280, 300));
        setBounds(250, 150, 380, 280);
        
        //Main Panel for the Frame
        mainPanel = new JPanel(new FlowLayout());
        add(mainPanel);
        
        mainPanel.add(new JLabel("Sprite Sheet Maker"));
        //mainPanel components
        upperPanel = new JPanel(new GridLayout(3, 4, 25, 10));
        lowerPanel = new JPanel(new FlowLayout());
        mainPanel.add(upperPanel);
        mainPanel.add(lowerPanel);
        
        //upperPanel components  
        nameField = new JTextField();
        rowField = new JTextField("1");
        columnField = new JTextField("1");
        maxWidthLabel = new JLabel(Integer.toString(maxWidth));
        maxHeightLabel = new JLabel(Integer.toString(maxHeight));
        upperPanel.add(new JLabel("File Name:"));
        upperPanel.add(nameField);
        upperPanel.add(new JLabel(""));
        upperPanel.add(new JLabel(""));
        upperPanel.add(new JLabel("Rows:"));
        upperPanel.add(rowField);
        upperPanel.add(new JLabel("Max Width: "));
        upperPanel.add(maxWidthLabel);
        upperPanel.add(new JLabel("Columns: "));
        upperPanel.add(columnField);
        upperPanel.add(new JLabel("Max Height: "));
        upperPanel.add(maxHeightLabel);
        
        //lowerPanel components
        listModel = new DefaultListModel();
        listBox = new JList(listModel);
        listBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listBox.setFocusable(false);
        buttonPanel = new JPanel(new GridLayout(4, 1, 5, 8));
        lowerPanel.add(new JScrollPane(listBox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        lowerPanel.add(buttonPanel);
        
        //buttonPanel components
        buttonPanel.add(addButton = new JButton("Add"));
        buttonPanel.add(removeButton = new JButton("Remove"));
        buttonPanel.add(clearButton = new JButton("Clear"));
        buttonPanel.add(saveButton = new JButton("Save"));
        
        //ActionListeners
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        clearButton.addActionListener(this);
        saveButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add"))
        {
            listModel.addElement("Hello");
        }
        if(e.getActionCommand().equals("Remove"))
        {
            if(!listBox.isSelectionEmpty())
            {
                int index = listBox.getSelectedIndex();
                if(listModel.getSize() - 1 < 1)
                {
                    listBox.setSelectedIndex(index - 1);
                }
                listModel.removeElementAt(index);
            }
        }
        if(e.getActionCommand().equals("Save"))
        {
            boolean isRowOk = textFieldVerification(rowField);
            boolean isColOk = textFieldVerification(columnField);
            
            if(isRowOk && isColOk)
            {
                JOptionPane.showMessageDialog(this, "Sprite Sheet saved to <file>.", "Succes!", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Invalid Row or Column.\nPlease correct them then try again.", "Failed!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if(e.getActionCommand().equals("Clear"))
        {
            listModel.removeAllElements();
        }
    }
    
    boolean textFieldVerification(JTextField textField)
    {
        int value = 0;
        
        try
        {
            value = Integer.parseInt(textField.getText());
        }
        catch(Exception ex)
        {
            textField.setBackground(Color.red);
            textField.setToolTipText("Not a valid number.");
            return false;
        }
        
        if(value < 1)
        {
            textField.setText("1");
        }
        
        textField.setBackground(Color.white);
        textField.setToolTipText(null);
        return true;
    }
    
    public static void main(String[] args)
    {
        JFrame frame = new spriteSheetGui();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
