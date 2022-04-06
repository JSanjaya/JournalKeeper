package ui;

import exceptions.InvalidDateException;
import model.Date;
import model.Entry;
import model.Journal;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Code taken from List Demo
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
// This class instantiates and creates the main GUI of the application

public class JournalKeeper extends JPanel
        implements ListSelectionListener {

    Journal journal;
    // Scanner input = new Scanner(System.in);
    // protected String password;
    JsonWriter writer = new JsonWriter("./data/journal.json");
    JsonReader reader = new JsonReader("./data/journal.json");
    private JList<Entry> guiList;
    private DefaultListModel listModel;
    private JButton addButton;
    private JButton removeButton;
    private JButton loadButton;
    private JButton saveButton;
    private JTextField entry;
    private JTextField day;
    private JTextField month;
    private JTextField year;
    JScrollPane listScrollPane;
    EntryListener entryListener;
    private JLabel entryText;
    private JLabel dayText;
    private JLabel monthText;
    private JLabel yearText;


    // EFFECTS: constructs new Journal Keeper object and instantiates the
    // new fields for the gui
    public JournalKeeper() {
        getJournal();
        System.out.println("\n");
        // this.password = "password";
        //runJournal();
        listModel = new DefaultListModel();

        guiList = new JList(listModel);
        guiList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        guiList.setSelectedIndex(0);
        guiList.addListSelectionListener(this);
        guiList.setVisibleRowCount(5);
        listScrollPane = new JScrollPane(guiList);

        setupButtons();
        setupTextFields();
        setupPanel();
//        JButton addButton = new JButton("Add");
//        EntryListener entryListener = new EntryListener(addButton);
//        addButton.setActionCommand("Add");
//        addButton.addActionListener(entryListener);
//        addButton.setEnabled(false);
//
//        removeButton = new JButton("Remove");
//        removeButton.setActionCommand("Remove");
//        removeButton.addActionListener(new DeletionListener());
//
//        loadButton = new JButton("Load");
//        loadButton.setActionCommand("Load");
//        loadButton.addActionListener(new LoadListener());
//
//        saveButton = new JButton("Save");
//        saveButton.setActionCommand("Save");
//        saveButton.addActionListener(new SaveListener());
//
//        entry = new JTextField(10);
//        entry.addActionListener(entryListener);
//        entry.getDocument().addDocumentListener(entryListener);
//        //String text = listModel.getElementAt(
//        //guiList.getSelectedIndex()).toString();
//        day = new JTextField(2);
//        day.addActionListener(entryListener);
//        day.getDocument().addDocumentListener(entryListener);
//
//        month = new JTextField(2);
//        month.addActionListener(entryListener);
//        month.getDocument().addDocumentListener(entryListener);
//
//        year = new JTextField(4);
//        year.addActionListener(entryListener);
//        year.getDocument().addDocumentListener(entryListener);

//        JPanel buttonPane = new JPanel();
//        buttonPane.setLayout(new BoxLayout(buttonPane,
//                BoxLayout.LINE_AXIS));
//        buttonPane.add(removeButton);
//        buttonPane.add(loadButton);
//        buttonPane.add(saveButton);
//        buttonPane.add(Box.createHorizontalStrut(5));
//        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
//        buttonPane.add(Box.createHorizontalStrut(5));
//        buttonPane.add(entry);
//        buttonPane.add(day);
//        buttonPane.add(month);
//        buttonPane.add(year);
//        buttonPane.add(addButton);
//        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//
//        add(listScrollPane, BorderLayout.CENTER);
//        add(buttonPane, BorderLayout.PAGE_END);


    }

    // EFFECTS: initializes buttons for the main GUI
    private void setupButtons() {
        addButton = new JButton("Add");
        entryListener = new EntryListener(addButton);
        addButton.setActionCommand("Add");
        addButton.addActionListener(entryListener);
        addButton.setEnabled(false);
        removeButton = new JButton("Remove");
        removeButton.setActionCommand("Remove");
        removeButton.addActionListener(new DeletionListener());
        loadButton = new JButton("Load");
        loadButton.setActionCommand("Load");
        loadButton.addActionListener(new LoadListener());
        saveButton = new JButton("Save");
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(new SaveListener());

    }

    // EFFECTS: initializes text fields for the main GUI
    private void setupTextFields() {
        entry = new JTextField(10);
        entry.addActionListener(entryListener);
        entry.getDocument().addDocumentListener(entryListener);
        day = new JTextField(2);
        day.addActionListener(entryListener);
        day.getDocument().addDocumentListener(entryListener);
        month = new JTextField(2);
        month.addActionListener(entryListener);
        month.getDocument().addDocumentListener(entryListener);
        year = new JTextField(4);
        year.addActionListener(entryListener);
        year.getDocument().addDocumentListener(entryListener);
        entryText = new JLabel("The text fields follow the format: Entry /");
        dayText = new JLabel(" Day /");
        monthText = new JLabel(" Month /");
        yearText = new JLabel(" Year ");
    }

    // EFFECTS: initializes the panel for the main GUI
    private void setupPanel() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(removeButton);
        buttonPane.add(loadButton);
        buttonPane.add(saveButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(entryText);
        buttonPane.add(dayText);
        buttonPane.add(monthText);
        buttonPane.add(yearText);
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(entry);
        buttonPane.add(day);
        buttonPane.add(month);
        buttonPane.add(year);
        buttonPane.add(addButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }


    // REQUIRES: option should be a number between 1 and 6 (inclusive)
    // EFFECTS: displays menu for user to interact with in the console, allows
    // user to choose between 6 options for them to use the application
    //public void runJournal() {
//        int option;
//        login();
//        System.out.println("Welcome to John's Journal Keeper");
//        do {
//            openMenu();
//            option = input.nextInt();
//            if (option == 1) {
//                viewAuthor();
//            } else if (option == 2) {
//                viewEntries();
//            } else if (option == 3) {
//                addEntry();
//            } else if (option == 4) {
//                deleteEntry();
//            } else if (option == 5) {
//                editEntry();
//            } else if (option == 6) {
//                executeJournal();
//            }
//        } while (!(option == 7));
//    }


    // EFFECTS: adds entry to list of entries in Journal
    private void addEntry(Entry e) {
//        System.out.println("Please enter the current date's day (i.e 1-31):");
//        int day = input.nextInt();
//        System.out.println("Please enter the current date's month (i.e 1-12):");
//        int month = input.nextInt();
//        System.out.println("Please enter the current date's year (i.e 2020):");
//        int year = input.nextInt();
//        System.out.println("Please enter your journal entry");
//        input.nextLine();
//        String entry = input.nextLine();
        journal.addEntry(e);

    }

    // EFFECTS: calls the saveJournal method and catches its exception
    private void executeJournal() {
        try {
            saveJournal();
        } catch (FileNotFoundException e) {
            System.out.print("");
        }
    }

    // MODIFIES: list of entries
    // EFFECTS: deletes desired entry from the Journal
//    private void deleteEntry() {
//        if (this.journal.getEntries().size() > 0) {
//            int option = selectEntry();
//            if (option >= 0 && option < journal.getEntries().size()) {
//                journal.removeEntry(journal.getEntries().remove(option));
//            }
//        }
//    }

    // EFFECTS: showcases which entry the user wants to select
    // and takes in the user's input
//    private int selectEntry() {
//        System.out.println("Choose an entry:");
//        for (int i = 0; i < journal.getEntries().size(); i++) {
//            System.out.println(i + " " + journal.getEntries().get(i).getEntry());
//        }
//        return input.nextInt();
//    }

    // EFFECTS: showcases menu used in runJournal
//    private void openMenu() {
//        System.out.println("Select from:");
//        System.out.println("1. View Author");
//        System.out.println("2. View Entries");
//        System.out.println("3. Add Entry");
//        System.out.println("4. Remove Entry");
//        System.out.println("5. Edit Entry");
//        System.out.println("6. Save Journal");
//        System.out.println("7. Quit");
//    }

    // MODIFIES: journal
    // EFFECTS: allows the user to edit either the day or entry for
    // a specific journal entry in the list of entries
//    private void editEntry() {
//        if (this.journal.getEntries().size() > 0) {
//            int option = selectEntry();
//            if (option >= 0 && option < journal.getEntries().size()) {
//                Entry entry = journal.getEntries().get(option);
//                System.out.println("What would you like to edit? \n 1. Date \n 2. Entry");
//                int editor = input.nextInt();
//                if (editor == 1) {
//                    System.out.println("Enter your new day (1-31)");
//                    int day = input.nextInt();
//                    System.out.println("Enter your new month (1-12)");
//                    int month = input.nextInt();
//                    System.out.println("Enter your new year (i.e 2020)");
//                    int year = input.nextInt();
//                    entry.setDate(new Date(day, month, year));
//                } else if (editor == 2) {
//                    System.out.println("Enter your new entry");
//                    input.nextLine();
//                    String newEntry = input.nextLine();
//                    entry.setEntry(newEntry);
//                }
//            }
//        }
//        journal.getEntries();
//    }

    // EFFECTS: loads Journal by reading the json
    private void loadJournal() throws IOException, InvalidDateException {
        journal = reader.read();
    }

    // EFFECTS: saves current Journal entries
    protected void saveJournal() throws FileNotFoundException {
        writer.open();
        writer.write(journal);
        writer.close();
    }

    // EFFECTS: loads Journal within try catch clause, if any exception is thrown,
    // a new blank Journal is created.
    private void getJournal() {
        try {
            loadJournal();
            //System.out.println("Previous Session Loaded");
        } catch (Exception e) {
            //System.out.println("Error when loading previous session. Creating new journal.");
            journal = new Journal("John");
        }
    }


    // EFFECTS: prompts user to enter the password, exits program if incorrect
//    private void login() {
//        System.out.println("Please enter the password: ");
//        String pass = input.nextLine();
//        if (pass.equals(password)) {
//            System.out.println("Password accepted.");
//        } else {
//            System.out.println("Wrong password, application will now exit");
//            System.exit(0);
//        }
//    }

    // EFFECTS: prints the viewAuthor method in Journal, allowing the user
    // to view the author's name and number of entries
//    private void viewAuthor() {
//        System.out.println(this.journal.viewAuthor());
//    }

    // EFFECTS: displays entries in the list of entries through the method
    // specified in the Journal class
//    private void viewEntries() {
//        System.out.println(this.journal.viewEntries());
//
//    }

    // EFFECTS: Takes in a ListSelectionEvent to determine
    // if the program should Enable/disable the remove button depending on
    // if the user selects an entry
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (guiList.getSelectedIndex() == -1) {
                //No selection, disable remove button.
                removeButton.setEnabled(false);

            } else {
                //Selection, enable the remove button.
                removeButton.setEnabled(true);
            }
        }
    }

    class EntryListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        // EFFECTS: Constructor that sets the JButton for the class
        public EntryListener(JButton button) {
            this.button = button;
        }

        // MODIFIES: Journal, guiList
        // EFFECTS: Adds an entry based on the information given in
        // the created text fields if the user clicks the add button.
        // Also adds the entered elements to the Journal object
        // and the Json file
        public void actionPerformed(ActionEvent e) {
            String text = entry.getText();
            String getDay = day.getText();
            String getMonth = month.getText();
            String getYear = year.getText();

            int index = guiList.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            String fullEntry = "";
            String date = (getDay + "/" + getMonth + "/" + getYear);
            fullEntry = fullEntry.concat("\n" + date + "\n" + text + "\n");

            listModel.insertElementAt(fullEntry, index);


            try {
                addEntry(new Entry(new Date((Integer.parseInt(getDay)),
                        (Integer.parseInt(getMonth)), (Integer.parseInt(getYear))), text));

            } catch (InvalidDateException i) {
                System.out.println("Invalid date. Invalid date entries will not be saved");
            }
            //Reset the text field.
            entry.setText("");

            //Select the new item and make it visible.
            guiList.setSelectedIndex(index);
            guiList.ensureIndexIsVisible(index);
        }


        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // EFFECTS: initializes buttons for the main GUI if the
        // entry text field has an element in it
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    class DeletionListener implements ActionListener {

        // MODIFIES: Journal, guiList
        // EFFECTS: deletes a selected entry from the GUI list if
        // the user selects a valid entry
        // Also deletes the selected element from the Journal object
        // and the Json file
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever is selected.
            int index = guiList.getSelectedIndex();
            listModel.remove(index);
            journal.getEntries().remove(index);

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                removeButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                guiList.setSelectedIndex(index);
                guiList.ensureIndexIsVisible(index);
            }
        }
    }

    class LoadListener implements ActionListener {

        // MODIFIES: Journal, guiList
        // EFFECTS: loads the saved list from the
        // the Json file and displays it on the GUI
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < journal.getEntries().size(); i++) {
                if (!listModel.contains(journal.getEntries().get(i))) {
                    String fullEntry = "";
                    String entry = (journal.getEntries().get(i).getEntry());
                    String date = (journal.getEntries().get(i).getDate().getDay() + "/"
                            + journal.getEntries().get(i).getDate().getMonth() + "/"
                            + journal.getEntries().get(i).getDate().getYear());
                    fullEntry = fullEntry.concat("\n" + date + "\n" + entry + "\n");
                    listModel.add(i, fullEntry);

                }

            }
            loadButton.setEnabled(false);
        }
    }

    class SaveListener implements ActionListener {

        // EFFECTS: calls the executeJournal method to save
        // the contents of journal to the corresponding Json file
        @Override
        public void actionPerformed(ActionEvent e) {
            executeJournal();
        }
    }

    // EFFECTS: creates a new JFrame object, and initializes
    // necessary elements to display the GUI in a window
    public void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("JournalKeeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new JournalKeeper();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


}
