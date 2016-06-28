/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunwarprabin.notepad.Gui;

import com.kunwarprabin.notepad.files.Files;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author OWNER
 */
public class AppGui extends JFrame {

    Files files = new Files();

    private JMenuBar menu;
    private JMenu file, edit, help;
    private JMenuItem newFile, openFile, save, saveAs, exit, clear, about;
    private JTextArea textArea;
    private JScrollPane scroll;
    private JFileChooser fileChooser;
    int choice = 0;
    String path = "";
    String content = "";

    public AppGui() {
        // * Managing Window Frame---
        setTitle("Notepad");
        setSize(500,500);
        setLayout(new FlowLayout(FlowLayout.LEADING));
        initGui();
        setVisible(true);
    }

    public void initGui() {
        // * Initializing MenuBar---
        menu = new JMenuBar();
        setJMenuBar(menu);
        menu.setBackground(Color.LIGHT_GRAY);
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");

        //* Adding menu in menu bar---
        menu.add(file);
        menu.add(edit);
        menu.add(help);

        // * initializing menu item
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        save = new JMenuItem("Save");
        saveAs = new JMenuItem("Save As");
        exit = new JMenuItem("Exit");
        clear = new JMenuItem("Clear");
        about = new JMenuItem("About Notepad");

        // * Adding menu item in menu
        file.add(newFile);
        file.add(openFile);
        file.add(save);
        file.add(saveAs);
        file.add(exit);
        edit.add(clear);
        help.add(about);

        textArea = new JTextArea(20,30);
        scroll = new JScrollPane(textArea);
        Container container = getContentPane();
        container.add(scroll);
        addListeners();
    }

    public void addListeners() {
        newFile.addActionListener(new NewFile());
        openFile.addActionListener(new OpenFile());
        save.addActionListener(new Save());
        saveAs.addActionListener(new SaveAs());
        exit.addActionListener(new Exit());
        clear.addActionListener(new Clear());
        about.addActionListener(new About());
    }

    public class NewFile implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!textArea.getText().equals("")) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to save your file", "C:\\Users\\OWNER\\Desktop\\temp.txt", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    SaveAs sa = new SaveAs();
                    sa.actionPerformed(e);
                    textArea.setText(null);
                    path = "";
                } else if (option == JOptionPane.NO_OPTION) {
                    textArea.setText(null);
                    path = "";
                }
            }
        }
    }

    public class Save implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            fileChooser = new JFileChooser("");
            if (path.equals("")) {
                choice = fileChooser.showSaveDialog(save);
                if (choice == fileChooser.APPROVE_OPTION) {
                    path = fileChooser.getSelectedFile().getPath();
                    files.writer(path, textArea.getText());
                }
            }
        }
    }

    public class SaveAs implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            fileChooser = new JFileChooser();
            choice = fileChooser.showSaveDialog(saveAs);
            if (choice == fileChooser.APPROVE_OPTION) {
                path = fileChooser.getSelectedFile().getPath();
                files.writer(path, textArea.getText());
            }
        }
    }

    public class OpenFile implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            fileChooser = new JFileChooser();
            int fileSelect = fileChooser.showOpenDialog(openFile);
            if (fileSelect == JFileChooser.APPROVE_OPTION) {
                textArea.setText(files.reader(fileChooser.getSelectedFile().getPath()));
            }
        }
    }

    public class Exit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!textArea.getText().equals("")) {
                int option = JOptionPane.showConfirmDialog(null, "Do you save before exit??", "C:\\Users\\OWNER\\Desktop\\temp.txt", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    SaveAs sa = new SaveAs();
                    sa.actionPerformed(e);

                } else if (option == JOptionPane.NO_OPTION) {

                    System.exit(0);
                }
            } else {
                System.exit(0);
            }
        }
    }

    public class Clear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.setText("");
        }
    }
    
    public class About implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Notepad Version: 1.1" +"\t\n"+ "Author:Prabin Kunwar");
        }
        
    }
}
