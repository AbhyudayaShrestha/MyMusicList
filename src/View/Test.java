/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;


import Controller.InitialSongsLoader;
import Controller.ManageProfiles;
import Controller.SongController;
import Controller.SongsForTodayTable;
import Controller.QueueController;
import Controller.RecentlyPlayedController;
import Model.MusicModel;
import Model.Song;
import Model.SongLibrary;

import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;



/**
 *
 * @author Abhyudaya Shrestha
 */
public class Test extends javax.swing.JFrame {
    private MusicModel n;
    private SongsForTodayTable i;
    private SongLibrary songLibrary;  // NEW
    private SongController songController;  // NEW
    private String currentUsername;
    private QueueController queueController;
    private RecentlyPlayedController recentlyPlayedController;

    public void updateTable(ArrayList<Song> songs) {
        DefaultTableModel model = (DefaultTableModel) songsForTodayTable.getModel();
        model.setRowCount(0);

        for (Song s : songs) {
            model.addRow(new Object[]{
                s.getSongName(),
                s.getArtistName(),
                s.getGenre(),
                s.getAlbum(),
                s.getReleasedYear()
            });
        }
    }
    // Helper method to update admin table with songs
    private void updateAdminTable() {
        DefaultTableModel model = (DefaultTableModel) songsForTodayTable1.getModel();
        model.setRowCount(0);

        for (Song s : n.getSongs()) {
            model.addRow(new Object[]{
                s.getSongName(),
                s.getArtistName(),
                s.getGenre(),
                s.getAlbum(),
                s.getReleasedYear()
            });
        }
    }
    // Update Admin Dashboard table (jTable2)
    private void updateAdminDashboard() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);

        for (Song s : songController.getAllSongs()) {
            model.addRow(new Object[]{
                s.getSongName(),
                s.getArtistName(),
                s.getGenre(),
                s.getAlbum(),
                s.getReleasedYear()
            });
        }
    }

// Update User Library table (jTable1)
    private void updateUserLibraryTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        for (Song s : songController.getAllSongs()) {
            model.addRow(new Object[]{
                s.getSongName(),
                s.getArtistName(),
                s.getGenre(),
                s.getAlbum(),
                s.getReleasedYear()
            });
        }
    }
    
  

        
    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Test.class.getName());

    /**
     * Creates new form NewJFrame
     */
    public Test() {
        initComponents();
        n = new MusicModel();
        i = new SongsForTodayTable(n, this);
        // Initialize song library system
    songLibrary = new SongLibrary();
    songController = new SongController(songLibrary);
    queueController = new QueueController();
    recentlyPlayedController = new RecentlyPlayedController();
    currentUsername = "";  // Will be set on login
        
    InitialSongsLoader.loadDemoSongs(songLibrary);
        
      
    }
        // ============ CRUD METHODS ============

// CREATE - Add new song
    private void addNewSong() {
    javax.swing.JTextField songNameField = new javax.swing.JTextField();
    javax.swing.JTextField artistField = new javax.swing.JTextField();
    javax.swing.JTextField genreField = new javax.swing.JTextField();
    javax.swing.JTextField albumField = new javax.swing.JTextField();
    javax.swing.JTextField yearField = new javax.swing.JTextField();
    
    Object[] message = {
        "Song Name:", songNameField,
        "Artist:", artistField,
        "Genre:", genreField,
        "Album:", albumField,
        "Released Year:", yearField
    };
    
    int option = JOptionPane.showConfirmDialog(this, message, "Add New Song", 
                                               JOptionPane.OK_CANCEL_OPTION);
    
    if (option == JOptionPane.OK_OPTION) {
        try {
            String songName = songNameField.getText().trim();
            String artist = artistField.getText().trim();
            String genre = genreField.getText().trim();
            String album = albumField.getText().trim();
            int year = Integer.parseInt(yearField.getText().trim());
            
            if (songController.createSong(songName, artist, genre, album, year)) {
                JOptionPane.showMessageDialog(this, "Song added successfully!", 
                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                updateAdminDashboard();
                updateUserLibraryTable();  // Sync to user view
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add song. Check if it already exists.", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid year format!", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

// UPDATE - Edit selected song
private void updateSelectedSong() {
    int selectedRow = jTable2.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a song to update!", 
                                    "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Get current values
    Song currentSong = songController.getSongAt(selectedRow);
    
    javax.swing.JTextField songNameField = new javax.swing.JTextField(currentSong.getSongName());
    javax.swing.JTextField artistField = new javax.swing.JTextField(currentSong.getArtistName());
    javax.swing.JTextField genreField = new javax.swing.JTextField(currentSong.getGenre());
    javax.swing.JTextField albumField = new javax.swing.JTextField(currentSong.getAlbum());
    javax.swing.JTextField yearField = new javax.swing.JTextField(String.valueOf(currentSong.getReleasedYear()));
    
    Object[] message = {
        "Song Name:", songNameField,
        "Artist:", artistField,
        "Genre:", genreField,
        "Album:", albumField,
        "Released Year:", yearField
    };
    
    int option = JOptionPane.showConfirmDialog(this, message, "Update Song", 
                                               JOptionPane.OK_CANCEL_OPTION);
    
    if (option == JOptionPane.OK_OPTION) {
        try {
            String songName = songNameField.getText().trim();
            String artist = artistField.getText().trim();
            String genre = genreField.getText().trim();
            String album = albumField.getText().trim();
            int year = Integer.parseInt(yearField.getText().trim());
            
            if (songController.updateSong(selectedRow, songName, artist, genre, album, year)) {
                JOptionPane.showMessageDialog(this, "Song updated successfully!", 
                                            "Success", JOptionPane.INFORMATION_MESSAGE);
                updateAdminDashboard();
                updateUserLibraryTable();  // Sync to user view
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update song!", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid year format!", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

// DELETE - Remove selected song
private void deleteSelectedSong() {
    int selectedRow = jTable2.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a song to delete!", 
                                    "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    Song song = songController.getSongAt(selectedRow);
    int confirm = JOptionPane.showConfirmDialog(this, 
        "Are you sure you want to delete:\n" + song.getSongName() + " by " + song.getArtistName() + "?",
        "Confirm Delete", JOptionPane.YES_NO_OPTION);
    
    if (confirm == JOptionPane.YES_OPTION) {
        if (songController.deleteSong(selectedRow)) {
            JOptionPane.showMessageDialog(this, "Song deleted successfully!", 
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
            updateAdminDashboard();
            updateUserLibraryTable();  // Sync to user view
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete song!", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}   // Update the user's playlist table
private void updateUserPlaylistTable() {
    DefaultTableModel model = (DefaultTableModel) PlaylistTable.getModel();
    model.setRowCount(0);

    ArrayList<Song> playlist = songLibrary.getUserPlaylistAsList(currentUsername);
    for (Song s : playlist) {
        model.addRow(new Object[]{
            s.getSongName(),
            s.getArtistName(),
            s.getGenre(),
            s.getAlbum(),
            s.getReleasedYear()
        });
    }
}

// Add song from library to user's playlist by typing song name
private void addSongToPlaylist() {
    ArrayList<Song> library = songController.getAllSongs();
    
    if (library.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No songs available in library!", 
                                    "Empty Library", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    String songName = JOptionPane.showInputDialog(this, 
        "Enter the song name to add to your playlist:", 
        "Add Song", 
        JOptionPane.QUESTION_MESSAGE);
    
    if (songName == null || songName.trim().isEmpty()) {
        return;
    }
    
    songName = songName.trim();
    
    int foundIndex = -1;
    Song foundSong = null;
    
    for (int i = 0; i < library.size(); i++) {
        if (library.get(i).getSongName().equalsIgnoreCase(songName)) {
            foundIndex = i;
            foundSong = library.get(i);
            break;
        }
    }
    
    if (foundIndex == -1) {
        JOptionPane.showMessageDialog(this, 
            "Song '" + songName + "' not found in Music Library!", 
            "Not Found", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    if (songLibrary.addToUserPlaylist(currentUsername, foundIndex)) {
        JOptionPane.showMessageDialog(this, 
            "'" + foundSong.getSongName() + "' by " + foundSong.getArtistName() + " added to playlist!", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
        updateUserPlaylistTable();
    } else {
        JOptionPane.showMessageDialog(this, "Failed to add song!", 
                                    "Error", JOptionPane.ERROR_MESSAGE);
    }
}

// Remove song from user's playlist by typing song name
private void removeSongFromPlaylist() {
    ArrayList<Song> playlist = songLibrary.getUserPlaylistAsList(currentUsername);
    
    if (playlist.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Your playlist is empty!", 
                                    "Empty Playlist", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    String songName = JOptionPane.showInputDialog(this, 
        "Enter the song name to remove from your playlist:", 
        "Remove Song", 
        JOptionPane.QUESTION_MESSAGE);
    
    if (songName == null || songName.trim().isEmpty()) {
        return;
    }
    
    songName = songName.trim();
    
    int foundIndex = -1;
    Song foundSong = null;
    
    for (int i = 0; i < playlist.size(); i++) {
        if (playlist.get(i).getSongName().equalsIgnoreCase(songName)) {
            foundIndex = i;
            foundSong = playlist.get(i);
            break;
        }
    }
    
    if (foundIndex == -1) {
        JOptionPane.showMessageDialog(this, 
            "Song '" + songName + "' not found in your playlist!", 
            "Not Found", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    int confirm = JOptionPane.showConfirmDialog(this, 
        "Remove '" + foundSong.getSongName() + "' by " + foundSong.getArtistName() + " from your playlist?",
        "Confirm Remove", 
        JOptionPane.YES_NO_OPTION);
    
    if (confirm == JOptionPane.YES_OPTION) {
        if (songLibrary.removeFromUserPlaylist(currentUsername, foundIndex)) {
            JOptionPane.showMessageDialog(this, "Song removed from playlist!", 
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
            updateUserPlaylistTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to remove song!", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
/**
 * Update the queue JList display
 */
private void updateQueueList() {
    DefaultListModel<String> listModel = new DefaultListModel<>();
    
    ArrayList<Song> queueSongs = queueController.getQueueList();
    Song currentSong = queueController.getCurrentSong();
    
    // Show currently playing song
    if (currentSong != null) {
        listModel.addElement(" NOW PLAYING: " + currentSong.getSongName() + " - " + currentSong.getArtistName());
    }
    
    // Show queue info
    listModel.addElement("Queue: " + queueController.getQueueSize() + "/" + queueController.getMaxQueueSize() + " songs");
    
    // Show queue
    if (queueSongs.isEmpty()) {
        listModel.addElement("Queue is empty");
    } else {
        int position = 1;
        for (Song song : queueSongs) {
            listModel.addElement(position + ". " + song.getSongName() + " - " + song.getArtistName());
            position++;
        }
    }
    
    jList1.setModel(listModel);
}
/**
 * Update the Recently Played JList display
 */
private void updateRecentlyPlayedList() {
    DefaultListModel<String> listModel = new DefaultListModel<>();
    
    ArrayList<Song> recentSongs = recentlyPlayedController.getRecentlyPlayedSongs();
    
    // Show header
    listModel.addElement("═══ Recently Played Stack ═══");
    listModel.addElement("Total: " + recentlyPlayedController.getRecentlyPlayedCount() + 
                        "/" + recentlyPlayedController.getMaxCapacity() + " songs");
    listModel.addElement(""); // Empty line
    
    // Show songs (most recent first)
    if (recentSongs.isEmpty()) {
        listModel.addElement("No songs played yet");
    } else {
        int position = 1;
        for (Song song : recentSongs) {
            listModel.addElement(position + ". " + song.getSongName() + 
                               " - " + song.getArtistName());
            position++;
        }
    }
    
    recentlyPlayedList.setModel(listModel);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        MainjPanel = new javax.swing.JPanel();
        LoginPanel = new javax.swing.JPanel();
        LoginBox = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        UsernameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        LoginButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        TitlePanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        RegisterPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        TitlePanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        UserJPanel = new javax.swing.JPanel();
        Buttons = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        Information = new javax.swing.JPanel();
        UserHome = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        songsForTodayTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        SongsPlaylist = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        PlaylistTable = new javax.swing.JTable();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        MusicLibrary = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel15 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        RecentlyPlayed = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        recentlyPlayedList = new javax.swing.JList<>();
        jLabel17 = new javax.swing.JLabel();
        Admin = new javax.swing.JPanel();
        ButtonsAdmin = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        LogOutAdmin = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        InformationAdmin = new javax.swing.JPanel();
        HomeAdmin = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        songsForTodayTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        MasterLibrary = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        UpdatePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MainjPanel.setLayout(new java.awt.CardLayout());

        LoginPanel.setBackground(java.awt.SystemColor.activeCaption);

        LoginBox.setBackground(new java.awt.Color(102, 102, 102));
        LoginBox.setMinimumSize(new java.awt.Dimension(516, 437));
        LoginBox.setPreferredSize(new java.awt.Dimension(516, 437));
        LoginBox.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                LoginBoxAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        LoginBox.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Log in");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 41, 0, 0);
        LoginBox.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Username:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 264, 0, 0);
        LoginBox.add(jLabel2, gridBagConstraints);

        UsernameField.setBackground(new java.awt.Color(102, 102, 102));
        UsernameField.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        UsernameField.setForeground(new java.awt.Color(255, 255, 255));
        UsernameField.setMinimumSize(new java.awt.Dimension(200, 30));
        UsernameField.setPreferredSize(new java.awt.Dimension(200, 30));
        UsernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 38, 0, 244);
        LoginBox.add(UsernameField, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(36, 265, 0, 0);
        LoginBox.add(jLabel3, gridBagConstraints);

        PasswordField.setBackground(new java.awt.Color(102, 102, 102));
        PasswordField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PasswordField.setForeground(new java.awt.Color(255, 255, 255));
        PasswordField.setMinimumSize(new java.awt.Dimension(200, 30));
        PasswordField.setPreferredSize(new java.awt.Dimension(200, 30));
        PasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 38, 0, 244);
        LoginBox.add(PasswordField, gridBagConstraints);

        LoginButton.setBackground(new java.awt.Color(0, 102, 153));
        LoginButton.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        LoginButton.setForeground(new java.awt.Color(255, 255, 255));
        LoginButton.setText("Login");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(47, 29, 0, 0);
        LoginBox.add(LoginButton, gridBagConstraints);

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Sign up");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 21, 32, 0);
        LoginBox.add(jButton2, gridBagConstraints);

        TitlePanel.setBackground(new java.awt.Color(102, 102, 102));
        TitlePanel.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 34)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("MyMusicList");

        javax.swing.GroupLayout TitlePanelLayout = new javax.swing.GroupLayout(TitlePanel);
        TitlePanel.setLayout(TitlePanelLayout);
        TitlePanelLayout.setHorizontalGroup(
            TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        TitlePanelLayout.setVerticalGroup(
            TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitlePanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 312, 0, 0);
        LoginBox.add(TitlePanel, gridBagConstraints);

        javax.swing.GroupLayout LoginPanelLayout = new javax.swing.GroupLayout(LoginPanel);
        LoginPanel.setLayout(LoginPanelLayout);
        LoginPanelLayout.setHorizontalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(LoginBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(167, 167, 167))
        );
        LoginPanelLayout.setVerticalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(LoginBox, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                .addGap(83, 83, 83))
        );

        MainjPanel.add(LoginPanel, "LoginCard");

        RegisterPanel.setBackground(java.awt.SystemColor.activeCaption);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(516, 437));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Register");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Username:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 40);
        jPanel1.add(jLabel7, gridBagConstraints);

        jTextField2.setBackground(new java.awt.Color(102, 102, 102));
        jTextField2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setMinimumSize(new java.awt.Dimension(200, 30));
        jTextField2.setPreferredSize(new java.awt.Dimension(200, 30));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel1.add(jTextField2, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Password:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 40);
        jPanel1.add(jLabel8, gridBagConstraints);

        jPasswordField2.setBackground(new java.awt.Color(102, 102, 102));
        jPasswordField2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPasswordField2.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField2.setMinimumSize(new java.awt.Dimension(200, 30));
        jPasswordField2.setPreferredSize(new java.awt.Dimension(200, 30));
        jPasswordField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        jPanel1.add(jPasswordField2, gridBagConstraints);

        TitlePanel1.setBackground(new java.awt.Color(102, 102, 102));
        TitlePanel1.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 34)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("MyMusicList");

        javax.swing.GroupLayout TitlePanel1Layout = new javax.swing.GroupLayout(TitlePanel1);
        TitlePanel1.setLayout(TitlePanel1Layout);
        TitlePanel1Layout.setHorizontalGroup(
            TitlePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        TitlePanel1Layout.setVerticalGroup(
            TitlePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitlePanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        jPanel1.add(TitlePanel1, gridBagConstraints);

        jButton4.setBackground(new java.awt.Color(0, 102, 204));
        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Register");
        jButton4.setPreferredSize(new java.awt.Dimension(119, 36));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        jPanel1.add(jButton4, gridBagConstraints);

        jButton5.setBackground(new java.awt.Color(0, 102, 153));
        jButton5.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Back to Login");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        jPanel1.add(jButton5, gridBagConstraints);

        javax.swing.GroupLayout RegisterPanelLayout = new javax.swing.GroupLayout(RegisterPanel);
        RegisterPanel.setLayout(RegisterPanelLayout);
        RegisterPanelLayout.setHorizontalGroup(
            RegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegisterPanelLayout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(133, 133, 133))
        );
        RegisterPanelLayout.setVerticalGroup(
            RegisterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegisterPanelLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(94, 94, 94))
        );

        MainjPanel.add(RegisterPanel, "RegisterCard");

        UserJPanel.setBackground(new java.awt.Color(102, 102, 102));
        UserJPanel.setLayout(new java.awt.BorderLayout());

        Buttons.setBackground(java.awt.SystemColor.activeCaption);

        jButton6.setBackground(new java.awt.Color(102, 153, 255));
        jButton6.setText("Home");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(102, 153, 255));
        jButton7.setText("Log out");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(102, 153, 255));
        jButton8.setText("Playlist");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(102, 153, 255));
        jButton9.setText("Music Library");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(102, 153, 255));
        jButton16.setText("Songs Queue");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton19.setBackground(new java.awt.Color(102, 153, 255));
        jButton19.setText("Recent Played");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ButtonsLayout = new javax.swing.GroupLayout(Buttons);
        Buttons.setLayout(ButtonsLayout);
        ButtonsLayout.setHorizontalGroup(
            ButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ButtonsLayout.createSequentialGroup()
                        .addGroup(ButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ButtonsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        ButtonsLayout.setVerticalGroup(
            ButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonsLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        UserJPanel.add(Buttons, java.awt.BorderLayout.LINE_START);

        Information.setBackground(java.awt.SystemColor.inactiveCaption);
        Information.setLayout(new java.awt.CardLayout());

        UserHome.setBackground(java.awt.SystemColor.inactiveCaption);

        songsForTodayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Song", "Artist", "Genre", "Album", "ReleasedYear"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        songsForTodayTable.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                songsForTodayTableComponentAdded(evt);
            }
        });
        jScrollPane1.setViewportView(songsForTodayTable);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Welcome Back!");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel10.setText("Top Songs For Today");

        javax.swing.GroupLayout UserHomeLayout = new javax.swing.GroupLayout(UserHome);
        UserHome.setLayout(UserHomeLayout);
        UserHomeLayout.setHorizontalGroup(
            UserHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserHomeLayout.createSequentialGroup()
                .addGroup(UserHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(UserHomeLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(35, 35, 35)))
                .addGap(357, 357, 357))
            .addGroup(UserHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        UserHomeLayout.setVerticalGroup(
            UserHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserHomeLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addGap(69, 69, 69))
        );

        Information.add(UserHome, "card2");

        SongsPlaylist.setBackground(java.awt.SystemColor.inactiveCaption);

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Add Songs");

        PlaylistTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Song", "Artist", "Genre", "Album", "ReleasedYear"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(PlaylistTable);

        jButton14.setText("Add Song");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Remove Song");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SongsPlaylistLayout = new javax.swing.GroupLayout(SongsPlaylist);
        SongsPlaylist.setLayout(SongsPlaylistLayout);
        SongsPlaylistLayout.setHorizontalGroup(
            SongsPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SongsPlaylistLayout.createSequentialGroup()
                .addGroup(SongsPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SongsPlaylistLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(SongsPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 183, Short.MAX_VALUE))
        );
        SongsPlaylistLayout.setVerticalGroup(
            SongsPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SongsPlaylistLayout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addGroup(SongsPlaylistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SongsPlaylistLayout.createSequentialGroup()
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 127, Short.MAX_VALUE))
        );

        Information.add(SongsPlaylist, "card3");

        MusicLibrary.setBackground(java.awt.SystemColor.inactiveCaption);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Song", "Artist", "Genre", "Album", "ReleasedYear"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout MusicLibraryLayout = new javax.swing.GroupLayout(MusicLibrary);
        MusicLibrary.setLayout(MusicLibraryLayout);
        MusicLibraryLayout.setHorizontalGroup(
            MusicLibraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MusicLibraryLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                .addGap(101, 101, 101))
        );
        MusicLibraryLayout.setVerticalGroup(
            MusicLibraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MusicLibraryLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addGap(113, 113, 113))
        );

        Information.add(MusicLibrary, "UserLibraryCard");

        jPanel2.setBackground(java.awt.SystemColor.inactiveCaption);

        jList1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jScrollPane6.setViewportView(jList1);

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Songs Playing");

        jButton17.setText("Play Next");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("Add to Queue");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 563, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        Information.add(jPanel2, "card5");

        RecentlyPlayed.setBackground(java.awt.SystemColor.inactiveCaption);

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Recenty Played Songs");

        jScrollPane7.setViewportView(recentlyPlayedList);

        jLabel17.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Take a look at the songs you just played");

        javax.swing.GroupLayout RecentlyPlayedLayout = new javax.swing.GroupLayout(RecentlyPlayed);
        RecentlyPlayed.setLayout(RecentlyPlayedLayout);
        RecentlyPlayedLayout.setHorizontalGroup(
            RecentlyPlayedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RecentlyPlayedLayout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(RecentlyPlayedLayout.createSequentialGroup()
                .addGroup(RecentlyPlayedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RecentlyPlayedLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel17))
                    .addGroup(RecentlyPlayedLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        RecentlyPlayedLayout.setVerticalGroup(
            RecentlyPlayedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RecentlyPlayedLayout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        Information.add(RecentlyPlayed, "card6");

        UserJPanel.add(Information, java.awt.BorderLayout.CENTER);

        MainjPanel.add(UserJPanel, "card4");

        Admin.setLayout(new java.awt.BorderLayout());

        ButtonsAdmin.setBackground(java.awt.SystemColor.activeCaption);
        ButtonsAdmin.setPreferredSize(new java.awt.Dimension(120, 262));

        jButton1.setBackground(new java.awt.Color(102, 153, 255));
        jButton1.setText("Home");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        LogOutAdmin.setBackground(new java.awt.Color(102, 153, 255));
        LogOutAdmin.setText("Log Out");
        LogOutAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutAdminActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(102, 153, 255));
        jButton10.setText("Music Library");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(102, 153, 255));
        jButton11.setText("Update Songs");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ButtonsAdminLayout = new javax.swing.GroupLayout(ButtonsAdmin);
        ButtonsAdmin.setLayout(ButtonsAdminLayout);
        ButtonsAdminLayout.setHorizontalGroup(
            ButtonsAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonsAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ButtonsAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LogOutAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        ButtonsAdminLayout.setVerticalGroup(
            ButtonsAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonsAdminLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addComponent(LogOutAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        Admin.add(ButtonsAdmin, java.awt.BorderLayout.LINE_START);

        InformationAdmin.setBackground(java.awt.SystemColor.inactiveCaption);
        InformationAdmin.setLayout(new java.awt.CardLayout());

        HomeAdmin.setBackground(java.awt.SystemColor.inactiveCaption);

        songsForTodayTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Song", "Artist", "Genre", "Album", "ReleasedYear"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        songsForTodayTable1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                songsForTodayTable1ComponentAdded(evt);
            }
        });
        jScrollPane2.setViewportView(songsForTodayTable1);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Welcome Back!");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel12.setText("Top Songs For Today");

        javax.swing.GroupLayout HomeAdminLayout = new javax.swing.GroupLayout(HomeAdmin);
        HomeAdmin.setLayout(HomeAdminLayout);
        HomeAdminLayout.setHorizontalGroup(
            HomeAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeAdminLayout.createSequentialGroup()
                .addGroup(HomeAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HomeAdminLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(HomeAdminLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(HomeAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        HomeAdminLayout.setVerticalGroup(
            HomeAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomeAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        InformationAdmin.add(HomeAdmin, "card2");

        MasterLibrary.setBackground(java.awt.SystemColor.inactiveCaption);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Song", "Artist", "Genre", "Album", "ReleasedYear"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel14.setText("Songs Library");

        jButton3.setBackground(java.awt.SystemColor.scrollbar);
        jButton3.setText("Update Song");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton12.setBackground(java.awt.SystemColor.scrollbar);
        jButton12.setText("Add Song");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(java.awt.SystemColor.scrollbar);
        jButton13.setText("Delete Song");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MasterLibraryLayout = new javax.swing.GroupLayout(MasterLibrary);
        MasterLibrary.setLayout(MasterLibraryLayout);
        MasterLibraryLayout.setHorizontalGroup(
            MasterLibraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MasterLibraryLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(499, 499, 499))
            .addGroup(MasterLibraryLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(MasterLibraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(MasterLibraryLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(MasterLibraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane4)
                .addGap(66, 66, 66))
        );
        MasterLibraryLayout.setVerticalGroup(
            MasterLibraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MasterLibraryLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(64, 64, 64)
                .addGroup(MasterLibraryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MasterLibraryLayout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(237, 237, 237))
                    .addGroup(MasterLibraryLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(161, Short.MAX_VALUE))))
        );

        InformationAdmin.add(MasterLibrary, "DashboardCard");

        UpdatePanel.setBackground(java.awt.SystemColor.inactiveCaption);

        javax.swing.GroupLayout UpdatePanelLayout = new javax.swing.GroupLayout(UpdatePanel);
        UpdatePanel.setLayout(UpdatePanelLayout);
        UpdatePanelLayout.setHorizontalGroup(
            UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 837, Short.MAX_VALUE)
        );
        UpdatePanelLayout.setVerticalGroup(
            UpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );

        InformationAdmin.add(UpdatePanel, "card4");

        Admin.add(InformationAdmin, java.awt.BorderLayout.CENTER);

        MainjPanel.add(Admin, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordFieldActionPerformed

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed

    String username = UsernameField.getText().trim();
    String password = new String(PasswordField.getPassword()).trim();
    
    // Create controller instance
    ManageProfiles adminController = new ManageProfiles(n);
    
    // Try admin login first
    ManageProfiles.ValidationResult result = adminController.validateLogin(username, password, true);
    
    if (result.success) {
         currentUsername = username;  // NEW - Save username
        // Show success message in VIEW
        JOptionPane.showMessageDialog(this, result.message, "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Admin login successful - switch to Admin panel
        CardLayout main = (CardLayout) MainjPanel.getLayout();
        main.show(MainjPanel, "card5"); // Admin card
        
        // Update admin home table
        updateAdminTable();
        updateAdminDashboard();
        
        // Clear fields
        UsernameField.setText("");
        PasswordField.setText("");
    } else {
        // Admin login failed, try user login
        result = adminController.validateLogin(username, password, false);
        
        if (result.success) {
            currentUsername = username;// NEW - Save username
            // Show success message in VIEW
            JOptionPane.showMessageDialog(this, result.message, "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // User login successful - switch to User panel
            CardLayout main = (CardLayout) MainjPanel.getLayout();
            main.show(MainjPanel, "card4"); // User card
            
            updateUserLibraryTable();  // NEW - Update user's view of library
            
            // Clear fields
            UsernameField.setText("");
            PasswordField.setText("");
        } else {
            // Both failed - show error in VIEW
            JOptionPane.showMessageDialog(this, result.message, "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }



    }//GEN-LAST:event_LoginButtonActionPerformed

    private void UsernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameFieldActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    CardLayout main = (CardLayout) MainjPanel.getLayout();
    main.show(MainjPanel, "RegisterCard");    // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jPasswordField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    String username = jTextField2.getText().trim();
    String password = new String(jPasswordField2.getPassword()).trim();
    
    ManageProfiles adminController = new ManageProfiles(n);
    
    // Register as regular user
    ManageProfiles.ValidationResult result = adminController.registerUser(username, password);
    
    if (result.success) {
        // Show success message in VIEW
        JOptionPane.showMessageDialog(this, result.message, "Registration Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Clear fields
        jTextField2.setText("");
        jPasswordField2.setText("");
        
        // Navigate back to login
        CardLayout main = (CardLayout) MainjPanel.getLayout();
        main.show(MainjPanel, "LoginCard");
    } else {
        // Show error in VIEW
        JOptionPane.showMessageDialog(this, result.message, "Registration Error", JOptionPane.ERROR_MESSAGE);
    }
    // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        CardLayout main = (CardLayout) MainjPanel.getLayout();
        main.show(MainjPanel, "LoginCard");    // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        CardLayout adminLayout = (CardLayout) Information.getLayout();
        adminLayout.show(Information, "card2");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        CardLayout main = (CardLayout) MainjPanel.getLayout();
        main.show(MainjPanel, "LoginCard");// TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void songsForTodayTableComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_songsForTodayTableComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_songsForTodayTableComponentAdded

    private void LoginBoxAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_LoginBoxAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_LoginBoxAncestorAdded

    private void songsForTodayTable1ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_songsForTodayTable1ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_songsForTodayTable1ComponentAdded

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CardLayout adminLayout = (CardLayout) InformationAdmin.getLayout();
        adminLayout.show(InformationAdmin, "card2");            // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void LogOutAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutAdminActionPerformed
        CardLayout main = (CardLayout) MainjPanel.getLayout();
        main.show(MainjPanel, "LoginCard");    // TODO add your handling code here:
    }//GEN-LAST:event_LogOutAdminActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        CardLayout adminLayout = (CardLayout) InformationAdmin.getLayout();
        adminLayout.show(InformationAdmin, "DashboardCard");
        updateAdminDashboard();  // Refresh the table     // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        CardLayout infoLayout = (CardLayout) Information.getLayout();
        infoLayout.show(Information, "card3");
        updateUserPlaylistTable();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        CardLayout infoLayout = (CardLayout) Information.getLayout();
        infoLayout.show(Information, "UserLibraryCard");
    // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        addNewSong();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        deleteSelectedSong();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        updateSelectedSong();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        addSongToPlaylist();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        removeSongFromPlaylist();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed

            CardLayout infoLayout = (CardLayout) Information.getLayout();
            infoLayout.show(Information, "card5");
            updateQueueList();  // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed

    ArrayList<Song> library = songController.getAllSongs();
    
    if (library.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "No songs available in library!", 
            "Empty Library", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    String songName = JOptionPane.showInputDialog(this, 
        "Enter the song name to add to queue:", 
        "Add to Queue", 
        JOptionPane.QUESTION_MESSAGE);
    
    if (songName == null || songName.trim().isEmpty()) {
        return;
    }
    
    songName = songName.trim();
    
    // Find song in library
    Song foundSong = null;
    for (Song song : library) {
        if (song.getSongName().equalsIgnoreCase(songName)) {
            foundSong = song;
            break;
        }
    }
    
    if (foundSong == null) {
        JOptionPane.showMessageDialog(this, 
            "Song '" + songName + "' not found in Music Library!", 
            "Not Found", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Add to queue with validation
    QueueController.QueueResult result = queueController.addToQueue(foundSong);
    
    if (result.success) {
        JOptionPane.showMessageDialog(this, 
            "'" + foundSong.getSongName() + "' by " + foundSong.getArtistName() + 
            " added to queue!", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
        updateQueueList();
    } else {
        // Show error message (queue full or other error)
        JOptionPane.showMessageDialog(this, 
            result.message, 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }            // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed

    QueueController.QueueResult result = queueController.playNextSong();
    
    if (result.success) {
        recentlyPlayedController.addPlayedSong(result.song);
        JOptionPane.showMessageDialog(this, 
            "Now Playing:\n" + result.song.getSongName() + " by " + result.song.getArtistName(), 
            "Playing Next", 
            JOptionPane.INFORMATION_MESSAGE);
        updateQueueList();
        updateRecentlyPlayedList();
    } else {
        JOptionPane.showMessageDialog(this, 
            result.message, 
            "Queue Empty", 
            JOptionPane.WARNING_MESSAGE);
        updateQueueList();
    }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        CardLayout infoLayout = (CardLayout) Information.getLayout();
    infoLayout.show(Information, "card6");
    updateRecentlyPlayedList();
    }//GEN-LAST:event_jButton19ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Test().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Admin;
    private javax.swing.JPanel Buttons;
    private javax.swing.JPanel ButtonsAdmin;
    private javax.swing.JPanel HomeAdmin;
    private javax.swing.JPanel Information;
    private javax.swing.JPanel InformationAdmin;
    private javax.swing.JButton LogOutAdmin;
    private javax.swing.JPanel LoginBox;
    private javax.swing.JButton LoginButton;
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JPanel MainjPanel;
    private javax.swing.JPanel MasterLibrary;
    private javax.swing.JPanel MusicLibrary;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JTable PlaylistTable;
    private javax.swing.JPanel RecentlyPlayed;
    private javax.swing.JPanel RegisterPanel;
    private javax.swing.JPanel SongsPlaylist;
    private javax.swing.JPanel TitlePanel;
    private javax.swing.JPanel TitlePanel1;
    private javax.swing.JPanel UpdatePanel;
    private javax.swing.JPanel UserHome;
    private javax.swing.JPanel UserJPanel;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JList<String> recentlyPlayedList;
    private javax.swing.JTable songsForTodayTable;
    private javax.swing.JTable songsForTodayTable1;
    // End of variables declaration//GEN-END:variables
}
