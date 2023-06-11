package Note_Taking_Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NoteTakingApp {

    private List<Note> notes;
    private JTextPane noteTextPane;
    private DefaultListModel<Note> noteListModel;

    public NoteTakingApp() {
        notes = new ArrayList<>();

        JFrame frame = new JFrame("Note Taking Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Create note text pane
        noteTextPane = new JTextPane();
        noteTextPane.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        noteTextPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.add(new JScrollPane(noteTextPane), BorderLayout.CENTER);

        // Create note list
        noteListModel = new DefaultListModel<>();
        JList<Note> noteList = new JList<>(noteListModel);
        noteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        noteList.setCellRenderer(new NoteListCellRenderer());
        noteList.setBorder(new EmptyBorder(10, 10, 10, 0));
        frame.add(new JScrollPane(noteList), BorderLayout.WEST);

        // Create add note button
        JButton addButton = new JButton("Add Note");
        addButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String noteText = noteTextPane.getText();
                if (!noteText.isEmpty()) {
                    Note note = new Note();
                    note.setText(noteText);
                    notes.add(note);
                    updateNoteListModel();
                    noteTextPane.setText("");
                }
            }
        });

        // Create delete note button
        JButton deleteButton = new JButton("Delete Note");
        deleteButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = noteList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    notes.remove(selectedIndex);
                    updateNoteListModel();
                }
            }
        });

        // Create favorite button
        JButton favoriteButton = new JButton("Favorite");
        favoriteButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        favoriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = noteList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Note note = notes.get(selectedIndex);
                    note.setFavorite(!note.isFavorite());
                    noteList.repaint();
                }
            }
        });

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(favoriteButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Add list selection listener to display selected note in the text pane
        noteList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = noteList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Note note = notes.get(selectedIndex);
                    noteTextPane.setText(note.getText());
                }
            }
        });

        frame.setVisible(true);
    }

    private void updateNoteListModel() {
        noteListModel.clear();
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            note.setSerialNumber(i + 1);
            noteListModel.addElement(note);
        }
    }

    private class Note {
        private String text;
        private boolean isFavorite;
        private int serialNumber;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isFavorite() {
            return isFavorite;
        }

        public void setFavorite(boolean favorite) {
            isFavorite = favorite;
        }

        public int getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(int serialNumber) {
            this.serialNumber = serialNumber;
        }

        @Override
        public String toString() {
            return (isFavorite ? "[Favorite] " : "") + serialNumber + ". " + text;
        }
    }

    private class NoteListCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Note note = (Note) value;
            label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
            label.setBorder(new EmptyBorder(5, 10, 5, 10));
            if (note.isFavorite()) {
                label.setForeground(Color.BLUE);
                label.setBackground(new Color(255, 255, 204));
            } else {
                label.setForeground(Color.BLACK);
                label.setBackground(Color.WHITE);
            }
            if (isSelected) {
                label.setBackground(new Color(153, 204, 255));
            }
            return label;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NoteTakingApp();
            }
        });
    }
}

