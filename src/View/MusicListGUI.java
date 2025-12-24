/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;



import Controller.AuthenticationController;
import Controller.LibraryController;
import Controller.PlaylistController;
import Controller.QueueController;
import Controller.SessionController;
import Controller.StackController;
import Model.SongModel;
import Model.LibraryModel;
import Model.UserModel;

import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;



/**
 *
 * @author Abhyudaya Shrestha
 */
public class MusicListGUI extends javax.swing.JFrame {
    private UserModel userModel;
    private LibraryModel songLibrary;
    private LibraryController libraryController;
    private PlaylistController playlistController;
    private SessionController sessionController;
    private AuthenticationController authController;    

    
    // Helper method to update admin table with songs
    private void updateUserHomeTable() {
    DefaultTableModel model = (DefaultTableModel) userHomeTable.getModel();
    model.setRowCount(0);
    
    model.addRow(new Object[]{"Bohemian Rhapsody", "Queen", "Rock", "A Night at the Opera", 1975});
    model.addRow(new Object[]{"Cant tell me nothing", "Kanye West", "Rap", "Graduation", 2007});
    model.addRow(new Object[]{"Last Christmas", "Wham", "Synth-pop", "Last Christmas", 1984});
    model.addRow(new Object[]{"Let Down", "RadioHead", "Rock", "OK Computer", 1997});
    model.addRow(new Object[]{"Country Roads", "John Denver", "Country", "Poems, Prayers and Promises", 1971});
}

    private void updateAdminHomeTable() {
    DefaultTableModel model = (DefaultTableModel) adminHomeTable.getModel();
    model.setRowCount(0);
    
    model.addRow(new Object[]{"Bohemian Rhapsody", "Queen", "Rock", "A Night at the Opera", 1975});
    model.addRow(new Object[]{"Cant tell me nothing", "Kanye West", "Rap", "Graduation", 2007});
    model.addRow(new Object[]{"Last Christmas", "Wham", "Synth-pop", "Last Christmas", 1984});
    model.addRow(new Object[]{"Let Down", "RadioHead", "Rock", "OK Computer", 1997});
    model.addRow(new Object[]{"Country Roads", "John Denver", "Country", "Poems, Prayers and Promises", 1971});
}
    // Update Admin Dashboard table (jTable2)
    private void updateAdminDashboard() {
        DefaultTableModel model = (DefaultTableModel) adminLibraryTable.getModel();
        model.setRowCount(0);

        for (SongModel s : libraryController.getAllSongs()) {
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
        DefaultTableModel model = (DefaultTableModel) userLibraryTable.getModel();
        model.setRowCount(0);

        for (SongModel s : libraryController.getAllSongs()) {
            model.addRow(new Object[]{
                s.getSongName(),
                s.getArtistName(),
                s.getGenre(),
                s.getAlbum(),
                s.getReleasedYear()
            });
        }
    }
    
  

        
    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MusicListGUI.class.getName());

    /**
     * Creates new form NewJFrame
     */
    public MusicListGUI() {
        initComponents();
        // Create models
    userModel = new UserModel();
    songLibrary = new LibraryModel();
    
    // Create controllers
    authController = new AuthenticationController(userModel);
    libraryController = new LibraryController(songLibrary);
    playlistController = new PlaylistController(songLibrary);
    sessionController = new SessionController();
    
    // Load demo songs
    libraryController.loadDemoSongs();
        
      
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
            
            if (libraryController.createSong(songName, artist, genre, album, year)) {
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
    int selectedRow = adminLibraryTable.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a song to update!", 
                                    "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Get current values
    SongModel currentSong = libraryController.getSongAt(selectedRow);
    
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
            
            if (libraryController.updateSong(selectedRow, songName, artist, genre, album, year)) {
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
    int selectedRow = adminLibraryTable.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a song to delete!", 
                                    "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    SongModel song = libraryController.getSongAt(selectedRow);
    int confirm = JOptionPane.showConfirmDialog(this, 
        "Are you sure you want to delete:\n" + song.getSongName() + " by " + song.getArtistName() + "?",
        "Confirm Delete", JOptionPane.YES_NO_OPTION);
    
    if (confirm == JOptionPane.YES_OPTION) {
        if (libraryController.deleteSong(selectedRow)) {
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
    if (!sessionController.hasActiveSession()) return;
    
    DefaultTableModel model = (DefaultTableModel) userPlaylistTable.getModel();
    model.setRowCount(0);

    // Get data via controller
    ArrayList<SongModel> playlist = 
        playlistController.getUserPlaylist(sessionController.getCurrentUsername());
    
    for (SongModel s : playlist) {
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
    if (!sessionController.hasActiveSession()) {
        JOptionPane.showMessageDialog(this, "Please login first!", 
            "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    String songName = JOptionPane.showInputDialog(this, 
        "Enter the song name to add to your playlist:", 
        "Add Song", 
        JOptionPane.QUESTION_MESSAGE);
    
    if (songName == null || songName.trim().isEmpty()) {
        return;
    }
    
    // Use controller instead of direct model access
    PlaylistController.PlaylistResult result = 
        playlistController.addSongToPlaylist(
            sessionController.getCurrentUsername(), 
            songName.trim());
    
    if (result.success) {
        JOptionPane.showMessageDialog(this, result.message, 
            "Success", JOptionPane.INFORMATION_MESSAGE);
        updateUserPlaylistTable();
    } else {
        JOptionPane.showMessageDialog(this, result.message, 
            "Error", JOptionPane.ERROR_MESSAGE);
    }
}

// Remove song from user's playlist by typing song name
private void removeSongFromPlaylist() {
    if (!sessionController.hasActiveSession()) {
        JOptionPane.showMessageDialog(this, "Please login first!", 
            "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    String songName = JOptionPane.showInputDialog(this, 
        "Enter the song name to remove from your playlist:", 
        "Remove Song", 
        JOptionPane.QUESTION_MESSAGE);
    
    if (songName == null || songName.trim().isEmpty()) {
        return;
    }
    
    // Use controller instead of direct model access
    PlaylistController.PlaylistResult result = 
        playlistController.removeSongFromPlaylist(
            sessionController.getCurrentUsername(), 
            songName.trim());
    
    if (result.success && result.song != null) {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Remove '" + result.song.getSongName() + "' by " + 
            result.song.getArtistName() + " from your playlist?",
            "Confirm Remove", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Song removed from playlist!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            updateUserPlaylistTable();
        }
    } else {
        JOptionPane.showMessageDialog(this, result.message, 
            "Error", JOptionPane.ERROR_MESSAGE);
    }
}
/**
 * Update the queue JList display
 */
private void updateQueueList() {
    if (!sessionController.hasActiveSession()) return;
    
    QueueController queueCtrl = sessionController.getCurrentQueueController();
    if (queueCtrl == null) return;
    
    DefaultListModel<String> listModel = new DefaultListModel<>();
    
    ArrayList<SongModel> queueSongs = queueCtrl.getQueueList();
    SongModel currentSong = queueCtrl.getCurrentSong();
    
    // Show currently playing song
    if (currentSong != null) {
        listModel.addElement("♪ NOW PLAYING: " + currentSong.getSongName() + " - " + currentSong.getArtistName());
    }
    
    // Show queue info
    listModel.addElement("Queue: " + queueCtrl.getQueueSize() + "/" + queueCtrl.getMaxQueueSize() + " songs");
    
    // Show queue
    if (queueSongs.isEmpty()) {
        listModel.addElement("Queue is empty");
    } else {
        int position = 1;
        for (SongModel song : queueSongs) {
            listModel.addElement(position + ". " + song.getSongName() + " - " + song.getArtistName());
            position++;
        }
    }
    
    userQueuedList.setModel(listModel);
}
/**
 * Update the Recently Played JList display
 */
private void updateRecentlyPlayedList() {
    if (!sessionController.hasActiveSession()) return;
    
    StackController stackCtrl = sessionController.getCurrentStackController();
    if (stackCtrl == null) return;
    
    DefaultListModel<String> listModel = new DefaultListModel<>();
    
    ArrayList<SongModel> recentSongs = stackCtrl.getRecentlyPlayedSongs();
    
    // Show header
    listModel.addElement("═══ Recently Played Stack ═══");
    listModel.addElement("Total: " + stackCtrl.getRecentlyPlayedCount() + 
                        "/" + stackCtrl.getMaxCapacity() + " songs");
    listModel.addElement(""); // Empty line
    
    // Show songs (most recent first)
    if (recentSongs.isEmpty()) {
        listModel.addElement("No songs played yet");
    } else {
        int position = 1;
        for (SongModel song : recentSongs) {
            listModel.addElement(position + ". " + song.getSongName() + 
                               " - " + song.getArtistName());
            position++;
        }
    }
    
    userRecentlyPlayedTable.setModel(listModel);
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

        mainPanel = new javax.swing.JPanel();
        loginPanel = new javax.swing.JPanel();
        loginBoxPanel = new javax.swing.JPanel();
        loginBoxTitlePanel = new javax.swing.JPanel();
        loginBoxMainTitleLabel = new javax.swing.JLabel();
        loginBoxTitleLabel = new javax.swing.JLabel();
        loginBoxUsernameLabel = new javax.swing.JLabel();
        loginBoxUsernameField = new javax.swing.JTextField();
        loginBoxPasswordLabel = new javax.swing.JLabel();
        loginBoxPasswordField = new javax.swing.JPasswordField();
        loginBoxLoginButton = new javax.swing.JButton();
        loginBoxSignInButton = new javax.swing.JButton();
        registerPanel = new javax.swing.JPanel();
        registerBoxPanel = new javax.swing.JPanel();
        registerBoxMainTitlePanel = new javax.swing.JPanel();
        registerBoxMainTitleLabel = new javax.swing.JLabel();
        registerBoxTitleLabel = new javax.swing.JLabel();
        registerBoxUsernameLabel = new javax.swing.JLabel();
        registerBoxUsernameField = new javax.swing.JTextField();
        registerBoxPasswordLabel = new javax.swing.JLabel();
        registerBoxPasswordField = new javax.swing.JPasswordField();
        registerBoxRegisterButton = new javax.swing.JButton();
        registerBoxBackToLoginButton = new javax.swing.JButton();
        userPanel = new javax.swing.JPanel();
        userButtonsPanel = new javax.swing.JPanel();
        userHomeButton = new javax.swing.JButton();
        userLibraryButton = new javax.swing.JButton();
        userPlaylistButton = new javax.swing.JButton();
        userSongsQueueButton = new javax.swing.JButton();
        userSongsStackButton = new javax.swing.JButton();
        userLogOutButton = new javax.swing.JButton();
        userCardPanel = new javax.swing.JPanel();
        userHomePanel = new javax.swing.JPanel();
        userHomeScrollPane = new javax.swing.JScrollPane();
        userHomeTable = new javax.swing.JTable();
        userHomeWelcomeLabel = new javax.swing.JLabel();
        userHomeTitleLabel = new javax.swing.JLabel();
        userHomeLogoLabel = new javax.swing.JLabel();
        userLibraryPanel = new javax.swing.JPanel();
        userLibraryScrollPane = new javax.swing.JScrollPane();
        userLibraryTable = new javax.swing.JTable();
        userLibraryTitleLabel = new javax.swing.JLabel();
        userLibrarySortButton = new javax.swing.JButton();
        userLibrarySearchButton = new javax.swing.JButton();
        userLibrarySearchField = new javax.swing.JTextField();
        userPlaylistPanel = new javax.swing.JPanel();
        userPlaylistTitleLabel = new javax.swing.JLabel();
        userPlaylistScrollPane = new javax.swing.JScrollPane();
        userPlaylistTable = new javax.swing.JTable();
        userPlaylistAddSongButton = new javax.swing.JButton();
        userPlaylistRemoveSongButton = new javax.swing.JButton();
        userQueuedSongsPanel = new javax.swing.JPanel();
        userQueuedScrollPane = new javax.swing.JScrollPane();
        userQueuedList = new javax.swing.JList<>();
        userQueuedTitleLabel = new javax.swing.JLabel();
        userQueuedAddToQueueButton = new javax.swing.JButton();
        userQueuedPlayNextButton = new javax.swing.JButton();
        userRecentlyPlayedPanel = new javax.swing.JPanel();
        userRecentlyPlayedTitleLabel = new javax.swing.JLabel();
        userRecentlyPlayedScrollPane = new javax.swing.JScrollPane();
        userRecentlyPlayedTable = new javax.swing.JList<>();
        userRecentlyPlayedSubTitleLabel = new javax.swing.JLabel();
        userRecentlyPlayedLastPlayedButton = new javax.swing.JButton();
        adminPanel = new javax.swing.JPanel();
        adminButtonsPanel = new javax.swing.JPanel();
        adminHomeButton = new javax.swing.JButton();
        adminLibraryButton = new javax.swing.JButton();
        LogOutAdmin = new javax.swing.JButton();
        adminCardPanel = new javax.swing.JPanel();
        adminHomePanel = new javax.swing.JPanel();
        adminHomeScrollPane = new javax.swing.JScrollPane();
        adminHomeTable = new javax.swing.JTable();
        adminHomeWelcomeLabel = new javax.swing.JLabel();
        adminHomeTitleLabel = new javax.swing.JLabel();
        adminHomeLogoLabel = new javax.swing.JLabel();
        adminLibraryPanel = new javax.swing.JPanel();
        adminLibraryScrollPane = new javax.swing.JScrollPane();
        adminLibraryTable = new javax.swing.JTable();
        adminLibraryTitleLabel = new javax.swing.JLabel();
        adminLibraryAddSongButton = new javax.swing.JButton();
        adminLibraryUpdateSongButton = new javax.swing.JButton();
        adminLibraryDeleteSongButton = new javax.swing.JButton();
        adminLibrarySearchField = new javax.swing.JTextField();
        adminLibrarySearchButton = new javax.swing.JButton();
        adminLibrarySortButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(new java.awt.CardLayout());

        loginPanel.setBackground(java.awt.SystemColor.activeCaption);
        loginPanel.setLayout(new java.awt.GridBagLayout());

        loginBoxPanel.setBackground(new java.awt.Color(102, 102, 102));
        loginBoxPanel.setMinimumSize(new java.awt.Dimension(516, 437));
        loginBoxPanel.setPreferredSize(new java.awt.Dimension(516, 437));
        loginBoxPanel.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                loginBoxPanelAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        loginBoxPanel.setLayout(new java.awt.GridBagLayout());

        loginBoxTitlePanel.setBackground(new java.awt.Color(102, 102, 102));
        loginBoxTitlePanel.setPreferredSize(new java.awt.Dimension(200, 100));

        loginBoxMainTitleLabel.setFont(new java.awt.Font("Times New Roman", 1, 34)); // NOI18N
        loginBoxMainTitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        loginBoxMainTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginBoxMainTitleLabel.setText("MyMusicList");

        javax.swing.GroupLayout loginBoxTitlePanelLayout = new javax.swing.GroupLayout(loginBoxTitlePanel);
        loginBoxTitlePanel.setLayout(loginBoxTitlePanelLayout);
        loginBoxTitlePanelLayout.setHorizontalGroup(
            loginBoxTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginBoxMainTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        loginBoxTitlePanelLayout.setVerticalGroup(
            loginBoxTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginBoxTitlePanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(loginBoxMainTitleLabel)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 312, 0, 0);
        loginBoxPanel.add(loginBoxTitlePanel, gridBagConstraints);

        loginBoxTitleLabel.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        loginBoxTitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        loginBoxTitleLabel.setText("Log in");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 41, 0, 0);
        loginBoxPanel.add(loginBoxTitleLabel, gridBagConstraints);

        loginBoxUsernameLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        loginBoxUsernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        loginBoxUsernameLabel.setText("Username:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 264, 0, 0);
        loginBoxPanel.add(loginBoxUsernameLabel, gridBagConstraints);

        loginBoxUsernameField.setBackground(new java.awt.Color(102, 102, 102));
        loginBoxUsernameField.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        loginBoxUsernameField.setForeground(new java.awt.Color(255, 255, 255));
        loginBoxUsernameField.setMinimumSize(new java.awt.Dimension(200, 30));
        loginBoxUsernameField.setPreferredSize(new java.awt.Dimension(200, 30));
        loginBoxUsernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBoxUsernameFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 38, 0, 244);
        loginBoxPanel.add(loginBoxUsernameField, gridBagConstraints);

        loginBoxPasswordLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        loginBoxPasswordLabel.setForeground(new java.awt.Color(255, 255, 255));
        loginBoxPasswordLabel.setText("Password:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(36, 265, 0, 0);
        loginBoxPanel.add(loginBoxPasswordLabel, gridBagConstraints);

        loginBoxPasswordField.setBackground(new java.awt.Color(102, 102, 102));
        loginBoxPasswordField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        loginBoxPasswordField.setForeground(new java.awt.Color(255, 255, 255));
        loginBoxPasswordField.setMinimumSize(new java.awt.Dimension(200, 30));
        loginBoxPasswordField.setPreferredSize(new java.awt.Dimension(200, 30));
        loginBoxPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBoxPasswordFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 38, 0, 244);
        loginBoxPanel.add(loginBoxPasswordField, gridBagConstraints);

        loginBoxLoginButton.setBackground(new java.awt.Color(0, 102, 153));
        loginBoxLoginButton.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        loginBoxLoginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginBoxLoginButton.setText("Login");
        loginBoxLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBoxLoginButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(47, 29, 0, 0);
        loginBoxPanel.add(loginBoxLoginButton, gridBagConstraints);

        loginBoxSignInButton.setBackground(new java.awt.Color(51, 51, 51));
        loginBoxSignInButton.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        loginBoxSignInButton.setForeground(new java.awt.Color(255, 255, 255));
        loginBoxSignInButton.setText("Sign up");
        loginBoxSignInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBoxSignInButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 21, 32, 0);
        loginBoxPanel.add(loginBoxSignInButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(106, 180, 155, 164);
        loginPanel.add(loginBoxPanel, gridBagConstraints);

        mainPanel.add(loginPanel, "LoginCard");

        registerPanel.setBackground(java.awt.SystemColor.activeCaption);
        registerPanel.setLayout(new java.awt.GridBagLayout());

        registerBoxPanel.setBackground(new java.awt.Color(102, 102, 102));
        registerBoxPanel.setPreferredSize(new java.awt.Dimension(516, 437));
        registerBoxPanel.setLayout(new java.awt.GridBagLayout());

        registerBoxMainTitlePanel.setBackground(new java.awt.Color(102, 102, 102));
        registerBoxMainTitlePanel.setPreferredSize(new java.awt.Dimension(200, 100));

        registerBoxMainTitleLabel.setFont(new java.awt.Font("Times New Roman", 1, 34)); // NOI18N
        registerBoxMainTitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        registerBoxMainTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerBoxMainTitleLabel.setText("MyMusicList");

        javax.swing.GroupLayout registerBoxMainTitlePanelLayout = new javax.swing.GroupLayout(registerBoxMainTitlePanel);
        registerBoxMainTitlePanel.setLayout(registerBoxMainTitlePanelLayout);
        registerBoxMainTitlePanelLayout.setHorizontalGroup(
            registerBoxMainTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(registerBoxMainTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        registerBoxMainTitlePanelLayout.setVerticalGroup(
            registerBoxMainTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerBoxMainTitlePanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(registerBoxMainTitleLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        registerBoxPanel.add(registerBoxMainTitlePanel, gridBagConstraints);

        registerBoxTitleLabel.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        registerBoxTitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        registerBoxTitleLabel.setText("Register");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        registerBoxPanel.add(registerBoxTitleLabel, gridBagConstraints);

        registerBoxUsernameLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        registerBoxUsernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        registerBoxUsernameLabel.setText("Username:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 40);
        registerBoxPanel.add(registerBoxUsernameLabel, gridBagConstraints);

        registerBoxUsernameField.setBackground(new java.awt.Color(102, 102, 102));
        registerBoxUsernameField.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        registerBoxUsernameField.setForeground(new java.awt.Color(255, 255, 255));
        registerBoxUsernameField.setMinimumSize(new java.awt.Dimension(200, 30));
        registerBoxUsernameField.setPreferredSize(new java.awt.Dimension(200, 30));
        registerBoxUsernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBoxUsernameFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        registerBoxPanel.add(registerBoxUsernameField, gridBagConstraints);

        registerBoxPasswordLabel.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        registerBoxPasswordLabel.setForeground(new java.awt.Color(255, 255, 255));
        registerBoxPasswordLabel.setText("Password:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 40);
        registerBoxPanel.add(registerBoxPasswordLabel, gridBagConstraints);

        registerBoxPasswordField.setBackground(new java.awt.Color(102, 102, 102));
        registerBoxPasswordField.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        registerBoxPasswordField.setForeground(new java.awt.Color(255, 255, 255));
        registerBoxPasswordField.setMinimumSize(new java.awt.Dimension(200, 30));
        registerBoxPasswordField.setPreferredSize(new java.awt.Dimension(200, 30));
        registerBoxPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBoxPasswordFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        registerBoxPanel.add(registerBoxPasswordField, gridBagConstraints);

        registerBoxRegisterButton.setBackground(new java.awt.Color(0, 102, 204));
        registerBoxRegisterButton.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        registerBoxRegisterButton.setForeground(new java.awt.Color(255, 255, 255));
        registerBoxRegisterButton.setText("Register");
        registerBoxRegisterButton.setPreferredSize(new java.awt.Dimension(119, 36));
        registerBoxRegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBoxRegisterButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        registerBoxPanel.add(registerBoxRegisterButton, gridBagConstraints);

        registerBoxBackToLoginButton.setBackground(new java.awt.Color(0, 102, 153));
        registerBoxBackToLoginButton.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        registerBoxBackToLoginButton.setForeground(new java.awt.Color(255, 255, 255));
        registerBoxBackToLoginButton.setText("Back to Login");
        registerBoxBackToLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBoxBackToLoginButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        registerBoxPanel.add(registerBoxBackToLoginButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 180;
        gridBagConstraints.ipady = 99;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(76, 185, 94, 133);
        registerPanel.add(registerBoxPanel, gridBagConstraints);

        mainPanel.add(registerPanel, "RegisterCard");

        userPanel.setBackground(new java.awt.Color(102, 102, 102));
        userPanel.setLayout(new java.awt.BorderLayout());

        userButtonsPanel.setBackground(java.awt.SystemColor.activeCaption);

        userHomeButton.setBackground(new java.awt.Color(102, 153, 255));
        userHomeButton.setText("Home");
        userHomeButton.setPreferredSize(new java.awt.Dimension(99, 23));
        userHomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userHomeButtonActionPerformed(evt);
            }
        });

        userLibraryButton.setBackground(new java.awt.Color(102, 153, 255));
        userLibraryButton.setText("Music Library");
        userLibraryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userLibraryButtonActionPerformed(evt);
            }
        });

        userPlaylistButton.setBackground(new java.awt.Color(102, 153, 255));
        userPlaylistButton.setText("Playlist");
        userPlaylistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userPlaylistButtonActionPerformed(evt);
            }
        });

        userSongsQueueButton.setBackground(new java.awt.Color(102, 153, 255));
        userSongsQueueButton.setText("Songs Queue");
        userSongsQueueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userSongsQueueButtonActionPerformed(evt);
            }
        });

        userSongsStackButton.setBackground(new java.awt.Color(102, 153, 255));
        userSongsStackButton.setText("Recent Played");
        userSongsStackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userSongsStackButtonActionPerformed(evt);
            }
        });

        userLogOutButton.setBackground(new java.awt.Color(102, 153, 255));
        userLogOutButton.setText("Log out");
        userLogOutButton.setMinimumSize(new java.awt.Dimension(99, 23));
        userLogOutButton.setPreferredSize(new java.awt.Dimension(99, 23));
        userLogOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userLogOutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout userButtonsPanelLayout = new javax.swing.GroupLayout(userButtonsPanel);
        userButtonsPanel.setLayout(userButtonsPanelLayout);
        userButtonsPanelLayout.setHorizontalGroup(
            userButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userButtonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userLogOutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userButtonsPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(userButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(userSongsQueueButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(userPlaylistButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(userLibraryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(userHomeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(userSongsStackButton, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        userButtonsPanelLayout.setVerticalGroup(
            userButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userButtonsPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(userHomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(userLibraryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(userPlaylistButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(userSongsQueueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(userSongsStackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(userLogOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        userPanel.add(userButtonsPanel, java.awt.BorderLayout.LINE_START);

        userCardPanel.setBackground(java.awt.SystemColor.inactiveCaption);
        userCardPanel.setLayout(new java.awt.CardLayout());

        userHomePanel.setBackground(java.awt.SystemColor.inactiveCaption);
        userHomePanel.setLayout(new java.awt.GridBagLayout());

        userHomeTable.setModel(new javax.swing.table.DefaultTableModel(
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
        userHomeTable.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                userHomeTableComponentAdded(evt);
            }
        });
        userHomeScrollPane.setViewportView(userHomeTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 717;
        gridBagConstraints.ipady = 406;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 6, 103, 6);
        userHomePanel.add(userHomeScrollPane, gridBagConstraints);

        userHomeWelcomeLabel.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        userHomeWelcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userHomeWelcomeLabel.setText("Welcome Back!");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 67;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 0, 0);
        userHomePanel.add(userHomeWelcomeLabel, gridBagConstraints);

        userHomeTitleLabel.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        userHomeTitleLabel.setText("Top Songs For Today");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 216;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(44, 6, 0, 0);
        userHomePanel.add(userHomeTitleLabel, gridBagConstraints);

        userHomeLogoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Screenshot 2025-12-24 174801.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(38, 12, 0, 0);
        userHomePanel.add(userHomeLogoLabel, gridBagConstraints);

        userCardPanel.add(userHomePanel, "card2");

        userLibraryPanel.setBackground(java.awt.SystemColor.inactiveCaption);
        userLibraryPanel.setLayout(new java.awt.GridBagLayout());

        userLibraryTable.setModel(new javax.swing.table.DefaultTableModel(
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
        userLibraryScrollPane.setViewportView(userLibraryTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 506;
        gridBagConstraints.ipady = 494;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 123, 44, 100);
        userLibraryPanel.add(userLibraryScrollPane, gridBagConstraints);

        userLibraryTitleLabel.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        userLibraryTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userLibraryTitleLabel.setText("Music Library");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 124;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 0, 0, 0);
        userLibraryPanel.add(userLibraryTitleLabel, gridBagConstraints);

        userLibrarySortButton.setBackground(java.awt.SystemColor.scrollbar);
        userLibrarySortButton.setText("Sort by ReleasedYear");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 123, 0, 0);
        userLibraryPanel.add(userLibrarySortButton, gridBagConstraints);

        userLibrarySearchButton.setBackground(java.awt.SystemColor.scrollbar);
        userLibrarySearchButton.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 18, 0, 100);
        userLibraryPanel.add(userLibrarySearchButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 62;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 81, 0, 0);
        userLibraryPanel.add(userLibrarySearchField, gridBagConstraints);

        userCardPanel.add(userLibraryPanel, "UserLibraryCard");

        userPlaylistPanel.setBackground(java.awt.SystemColor.inactiveCaption);
        userPlaylistPanel.setLayout(new java.awt.GridBagLayout());

        userPlaylistTitleLabel.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        userPlaylistTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userPlaylistTitleLabel.setText("Add Songs");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 111;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        userPlaylistPanel.add(userPlaylistTitleLabel, gridBagConstraints);

        userPlaylistTable.setModel(new javax.swing.table.DefaultTableModel(
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
        userPlaylistScrollPane.setViewportView(userPlaylistTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 482;
        gridBagConstraints.ipady = 380;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(30, 18, 185, 66);
        userPlaylistPanel.add(userPlaylistScrollPane, gridBagConstraints);

        userPlaylistAddSongButton.setBackground(java.awt.SystemColor.scrollbar);
        userPlaylistAddSongButton.setText("Add Song");
        userPlaylistAddSongButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userPlaylistAddSongButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 48;
        gridBagConstraints.ipady = 48;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(39, 31, 0, 0);
        userPlaylistPanel.add(userPlaylistAddSongButton, gridBagConstraints);

        userPlaylistRemoveSongButton.setBackground(java.awt.SystemColor.scrollbar);
        userPlaylistRemoveSongButton.setText("Remove Song");
        userPlaylistRemoveSongButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userPlaylistRemoveSongButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 47;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(44, 31, 0, 0);
        userPlaylistPanel.add(userPlaylistRemoveSongButton, gridBagConstraints);

        userCardPanel.add(userPlaylistPanel, "card3");

        userQueuedSongsPanel.setBackground(java.awt.SystemColor.inactiveCaption);

        userQueuedList.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        userQueuedScrollPane.setViewportView(userQueuedList);

        userQueuedTitleLabel.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        userQueuedTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userQueuedTitleLabel.setText("Songs Playing");

        userQueuedAddToQueueButton.setBackground(java.awt.SystemColor.scrollbar);
        userQueuedAddToQueueButton.setText("Add to Queue");
        userQueuedAddToQueueButton.setMinimumSize(new java.awt.Dimension(90, 10));
        userQueuedAddToQueueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userQueuedAddToQueueButtonActionPerformed(evt);
            }
        });

        userQueuedPlayNextButton.setBackground(java.awt.SystemColor.scrollbar);
        userQueuedPlayNextButton.setText("Play Next");
        userQueuedPlayNextButton.setMaximumSize(new java.awt.Dimension(90, 10));
        userQueuedPlayNextButton.setMinimumSize(new java.awt.Dimension(90, 23));
        userQueuedPlayNextButton.setPreferredSize(new java.awt.Dimension(80, 10));
        userQueuedPlayNextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userQueuedPlayNextButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout userQueuedSongsPanelLayout = new javax.swing.GroupLayout(userQueuedSongsPanel);
        userQueuedSongsPanel.setLayout(userQueuedSongsPanelLayout);
        userQueuedSongsPanelLayout.setHorizontalGroup(
            userQueuedSongsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userQueuedTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(userQueuedSongsPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(userQueuedSongsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userQueuedPlayNextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userQueuedAddToQueueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(userQueuedScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        userQueuedSongsPanelLayout.setVerticalGroup(
            userQueuedSongsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userQueuedSongsPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(userQueuedTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(userQueuedSongsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userQueuedSongsPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(userQueuedAddToQueueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(userQueuedPlayNextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(userQueuedScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        userCardPanel.add(userQueuedSongsPanel, "card5");

        userRecentlyPlayedPanel.setBackground(java.awt.SystemColor.inactiveCaption);

        userRecentlyPlayedTitleLabel.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        userRecentlyPlayedTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userRecentlyPlayedTitleLabel.setText("Recenty Played Songs");

        userRecentlyPlayedScrollPane.setViewportView(userRecentlyPlayedTable);

        userRecentlyPlayedSubTitleLabel.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        userRecentlyPlayedSubTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userRecentlyPlayedSubTitleLabel.setText("Take a look at the songs you just played");

        userRecentlyPlayedLastPlayedButton.setBackground(java.awt.SystemColor.scrollbar);
        userRecentlyPlayedLastPlayedButton.setText("Last Played Song");
        userRecentlyPlayedLastPlayedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userRecentlyPlayedLastPlayedButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout userRecentlyPlayedPanelLayout = new javax.swing.GroupLayout(userRecentlyPlayedPanel);
        userRecentlyPlayedPanel.setLayout(userRecentlyPlayedPanelLayout);
        userRecentlyPlayedPanelLayout.setHorizontalGroup(
            userRecentlyPlayedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userRecentlyPlayedPanelLayout.createSequentialGroup()
                .addGroup(userRecentlyPlayedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(userRecentlyPlayedPanelLayout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(userRecentlyPlayedScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(userRecentlyPlayedPanelLayout.createSequentialGroup()
                        .addGroup(userRecentlyPlayedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userRecentlyPlayedTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(userRecentlyPlayedPanelLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(userRecentlyPlayedSubTitleLabel)))
                        .addGap(251, 251, 251)
                        .addComponent(userRecentlyPlayedLastPlayedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        userRecentlyPlayedPanelLayout.setVerticalGroup(
            userRecentlyPlayedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userRecentlyPlayedPanelLayout.createSequentialGroup()
                .addGroup(userRecentlyPlayedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userRecentlyPlayedPanelLayout.createSequentialGroup()
                        .addComponent(userRecentlyPlayedTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(userRecentlyPlayedSubTitleLabel)
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userRecentlyPlayedPanelLayout.createSequentialGroup()
                        .addComponent(userRecentlyPlayedLastPlayedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(userRecentlyPlayedScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
        );

        userCardPanel.add(userRecentlyPlayedPanel, "card6");

        userPanel.add(userCardPanel, java.awt.BorderLayout.CENTER);

        mainPanel.add(userPanel, "card4");

        adminPanel.setLayout(new java.awt.BorderLayout());

        adminButtonsPanel.setBackground(java.awt.SystemColor.activeCaption);
        adminButtonsPanel.setPreferredSize(new java.awt.Dimension(120, 262));
        adminButtonsPanel.setLayout(new java.awt.GridBagLayout());

        adminHomeButton.setBackground(new java.awt.Color(102, 153, 255));
        adminHomeButton.setText("Home");
        adminHomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminHomeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.ipady = 39;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(31, 6, 0, 9);
        adminButtonsPanel.add(adminHomeButton, gridBagConstraints);

        adminLibraryButton.setBackground(new java.awt.Color(102, 153, 255));
        adminLibraryButton.setText("Music Library");
        adminLibraryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminLibraryButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 39;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(77, 6, 0, 9);
        adminButtonsPanel.add(adminLibraryButton, gridBagConstraints);

        LogOutAdmin.setBackground(new java.awt.Color(102, 153, 255));
        LogOutAdmin.setText("Log Out");
        LogOutAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutAdminActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 32;
        gridBagConstraints.ipady = 36;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(92, 6, 329, 9);
        adminButtonsPanel.add(LogOutAdmin, gridBagConstraints);

        adminPanel.add(adminButtonsPanel, java.awt.BorderLayout.LINE_START);

        adminCardPanel.setBackground(java.awt.SystemColor.inactiveCaption);
        adminCardPanel.setLayout(new java.awt.CardLayout());

        adminHomePanel.setBackground(java.awt.SystemColor.inactiveCaption);
        adminHomePanel.setLayout(new java.awt.GridBagLayout());

        adminHomeTable.setModel(new javax.swing.table.DefaultTableModel(
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
        adminHomeTable.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                adminHomeTableComponentAdded(evt);
            }
        });
        adminHomeScrollPane.setViewportView(adminHomeTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 662;
        gridBagConstraints.ipady = 370;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(48, 36, 93, 26);
        adminHomePanel.add(adminHomeScrollPane, gridBagConstraints);

        adminHomeWelcomeLabel.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        adminHomeWelcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminHomeWelcomeLabel.setText("Welcome Back!");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        adminHomePanel.add(adminHomeWelcomeLabel, gridBagConstraints);

        adminHomeTitleLabel.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        adminHomeTitleLabel.setText("Top Songs For Today");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(49, 36, 0, 0);
        adminHomePanel.add(adminHomeTitleLabel, gridBagConstraints);

        adminHomeLogoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Screenshot 2025-12-24 174801.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(45, 151, 0, 0);
        adminHomePanel.add(adminHomeLogoLabel, gridBagConstraints);

        adminCardPanel.add(adminHomePanel, "card2");

        adminLibraryPanel.setBackground(java.awt.SystemColor.inactiveCaption);
        adminLibraryPanel.setLayout(new java.awt.GridBagLayout());

        adminLibraryTable.setModel(new javax.swing.table.DefaultTableModel(
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
        adminLibraryScrollPane.setViewportView(adminLibraryTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 502;
        gridBagConstraints.ipady = 445;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 18, 6, 95);
        adminLibraryPanel.add(adminLibraryScrollPane, gridBagConstraints);

        adminLibraryTitleLabel.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        adminLibraryTitleLabel.setText("Songs Library");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.ipady = 27;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        adminLibraryPanel.add(adminLibraryTitleLabel, gridBagConstraints);

        adminLibraryAddSongButton.setBackground(java.awt.SystemColor.scrollbar);
        adminLibraryAddSongButton.setText("Add Song");
        adminLibraryAddSongButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminLibraryAddSongButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 29;
        gridBagConstraints.ipady = 37;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 42, 0, 0);
        adminLibraryPanel.add(adminLibraryAddSongButton, gridBagConstraints);

        adminLibraryUpdateSongButton.setBackground(java.awt.SystemColor.scrollbar);
        adminLibraryUpdateSongButton.setText("Update Song");
        adminLibraryUpdateSongButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminLibraryUpdateSongButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(49, 42, 0, 0);
        adminLibraryPanel.add(adminLibraryUpdateSongButton, gridBagConstraints);

        adminLibraryDeleteSongButton.setBackground(java.awt.SystemColor.scrollbar);
        adminLibraryDeleteSongButton.setText("Delete Song");
        adminLibraryDeleteSongButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminLibraryDeleteSongButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 21;
        gridBagConstraints.ipady = 36;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(55, 39, 0, 0);
        adminLibraryPanel.add(adminLibraryDeleteSongButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 62;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 161, 0, 0);
        adminLibraryPanel.add(adminLibrarySearchField, gridBagConstraints);

        adminLibrarySearchButton.setBackground(java.awt.SystemColor.scrollbar);
        adminLibrarySearchButton.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 18, 0, 95);
        adminLibraryPanel.add(adminLibrarySearchButton, gridBagConstraints);

        adminLibrarySortButton.setBackground(java.awt.SystemColor.scrollbar);
        adminLibrarySortButton.setText("Sort by ReleasedYear");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 18, 0, 0);
        adminLibraryPanel.add(adminLibrarySortButton, gridBagConstraints);

        adminCardPanel.add(adminLibraryPanel, "DashboardCard");

        adminPanel.add(adminCardPanel, java.awt.BorderLayout.CENTER);

        mainPanel.add(adminPanel, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 906, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginBoxPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBoxPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginBoxPasswordFieldActionPerformed

    private void loginBoxLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBoxLoginButtonActionPerformed

       String username = loginBoxUsernameField.getText().trim();
    String password = new String(loginBoxPasswordField.getPassword()).trim();
    
    AuthenticationController adminController = new AuthenticationController(userModel);
    
    // Try admin login first
    AuthenticationController.ValidationResult result = authController.validateLogin(username, password, true);
    
    if (result.success) {
        // Start session via controller
        sessionController.startSession(username);
        
        JOptionPane.showMessageDialog(this, result.message, "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Update welcome label with username
        adminHomeWelcomeLabel.setText("Welcome Back, " + username + "!");
        
        // Admin login successful - switch to Admin panel
        CardLayout main = (CardLayout) mainPanel.getLayout();
        main.show(mainPanel, "card5");
        
        updateAdminHomeTable();
        updateAdminDashboard();
        
        // Clear fields
        loginBoxUsernameField.setText("");
        loginBoxPasswordField.setText("");
    } else {
        // Admin login failed, try user login
        result = authController.validateLogin(username, password, false);
        
        if (result.success) {
            // Start session via controller
            sessionController.startSession(username);
            
            JOptionPane.showMessageDialog(this, result.message, "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Update welcome label with username
            userHomeWelcomeLabel.setText("Welcome Back, " + username + "!");
            
            // User login successful - switch to User panel
            CardLayout main = (CardLayout) mainPanel.getLayout();
            main.show(mainPanel, "card4");
            
            updateUserHomeTable(); 
            updateUserLibraryTable();
            
            // Clear fields
            loginBoxUsernameField.setText("");
            loginBoxPasswordField.setText("");
        } else {
            // Both failed - show error
            JOptionPane.showMessageDialog(this, result.message, "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }



    }//GEN-LAST:event_loginBoxLoginButtonActionPerformed

    private void loginBoxUsernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBoxUsernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginBoxUsernameFieldActionPerformed

    private void loginBoxSignInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBoxSignInButtonActionPerformed
    CardLayout main = (CardLayout) mainPanel.getLayout();
    main.show(mainPanel, "RegisterCard");    // TODO add your handling code here:
    }//GEN-LAST:event_loginBoxSignInButtonActionPerformed

    private void registerBoxUsernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBoxUsernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerBoxUsernameFieldActionPerformed

    private void registerBoxPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBoxPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registerBoxPasswordFieldActionPerformed

    private void registerBoxRegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBoxRegisterButtonActionPerformed

    String username = registerBoxUsernameField.getText().trim();
    String password = new String(registerBoxPasswordField.getPassword()).trim();
    
    // Register as regular user
    AuthenticationController.ValidationResult result = authController.registerUser(username, password);
    
    if (result.success) {
        // Show success message in VIEW
        JOptionPane.showMessageDialog(this, result.message, "Registration Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Clear fields
        registerBoxUsernameField.setText("");
        registerBoxPasswordField.setText("");
        
        // Navigate back to login
        CardLayout main = (CardLayout) mainPanel.getLayout();
        main.show(mainPanel, "LoginCard");
    } else {
        // Show error in VIEW
        JOptionPane.showMessageDialog(this, result.message, "Registration Error", JOptionPane.ERROR_MESSAGE);
    }
    // TODO add your handling code here:
    }//GEN-LAST:event_registerBoxRegisterButtonActionPerformed

    private void registerBoxBackToLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBoxBackToLoginButtonActionPerformed
        CardLayout main = (CardLayout) mainPanel.getLayout();
        main.show(mainPanel, "LoginCard");    // TODO add your handling code here:
    }//GEN-LAST:event_registerBoxBackToLoginButtonActionPerformed

    private void userHomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userHomeButtonActionPerformed
        CardLayout adminLayout = (CardLayout) userCardPanel.getLayout();
        adminLayout.show(userCardPanel, "card2");
              // TODO add your handling code here:
    }//GEN-LAST:event_userHomeButtonActionPerformed

    private void userLogOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userLogOutButtonActionPerformed

    int choice = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to log out?",
        "Confirm Logout",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
    );
    
    if (choice == JOptionPane.YES_OPTION) {
        // End session via controller
        sessionController.endSession();
        
        CardLayout main = (CardLayout) mainPanel.getLayout();
        main.show(mainPanel, "LoginCard");
    }
    }//GEN-LAST:event_userLogOutButtonActionPerformed

    private void userHomeTableComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_userHomeTableComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_userHomeTableComponentAdded

    private void loginBoxPanelAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_loginBoxPanelAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_loginBoxPanelAncestorAdded

    private void adminHomeTableComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_adminHomeTableComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_adminHomeTableComponentAdded

    private void adminHomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminHomeButtonActionPerformed
        CardLayout adminLayout = (CardLayout) adminCardPanel.getLayout();
        adminLayout.show(adminCardPanel, "card2");            // TODO add your handling code here:
    }//GEN-LAST:event_adminHomeButtonActionPerformed

    private void LogOutAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutAdminActionPerformed

    int choice = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to log out?",
        "Confirm Logout",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
    );
    
    if (choice == JOptionPane.YES_OPTION) {
        // End session via controller
        sessionController.endSession();
        
        CardLayout main = (CardLayout) mainPanel.getLayout();
        main.show(mainPanel, "LoginCard");
    }
    }//GEN-LAST:event_LogOutAdminActionPerformed

    private void adminLibraryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminLibraryButtonActionPerformed
        CardLayout adminLayout = (CardLayout) adminCardPanel.getLayout();
        adminLayout.show(adminCardPanel, "DashboardCard");
        updateAdminDashboard();  // Refresh the table     // TODO add your handling code here:
    }//GEN-LAST:event_adminLibraryButtonActionPerformed

    private void userPlaylistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userPlaylistButtonActionPerformed
        CardLayout infoLayout = (CardLayout) userCardPanel.getLayout();
        infoLayout.show(userCardPanel, "card3");
        updateUserPlaylistTable();        // TODO add your handling code here:
    }//GEN-LAST:event_userPlaylistButtonActionPerformed

    private void userLibraryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userLibraryButtonActionPerformed
        CardLayout infoLayout = (CardLayout) userCardPanel.getLayout();
        infoLayout.show(userCardPanel, "UserLibraryCard");
    // TODO add your handling code here:
    }//GEN-LAST:event_userLibraryButtonActionPerformed

    private void adminLibraryAddSongButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminLibraryAddSongButtonActionPerformed
        addNewSong();        // TODO add your handling code here:
    }//GEN-LAST:event_adminLibraryAddSongButtonActionPerformed

    private void adminLibraryDeleteSongButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminLibraryDeleteSongButtonActionPerformed
        deleteSelectedSong();        // TODO add your handling code here:
    }//GEN-LAST:event_adminLibraryDeleteSongButtonActionPerformed

    private void adminLibraryUpdateSongButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminLibraryUpdateSongButtonActionPerformed
        updateSelectedSong();        // TODO add your handling code here:
    }//GEN-LAST:event_adminLibraryUpdateSongButtonActionPerformed

    private void userPlaylistAddSongButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userPlaylistAddSongButtonActionPerformed
        addSongToPlaylist();        // TODO add your handling code here:
    }//GEN-LAST:event_userPlaylistAddSongButtonActionPerformed

    private void userPlaylistRemoveSongButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userPlaylistRemoveSongButtonActionPerformed
        removeSongFromPlaylist();        // TODO add your handling code here:
    }//GEN-LAST:event_userPlaylistRemoveSongButtonActionPerformed

    private void userSongsQueueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userSongsQueueButtonActionPerformed

            CardLayout infoLayout = (CardLayout) userCardPanel.getLayout();
            infoLayout.show(userCardPanel, "card5");
            updateQueueList();  // TODO add your handling code here:
    }//GEN-LAST:event_userSongsQueueButtonActionPerformed

    private void userQueuedAddToQueueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userQueuedAddToQueueButtonActionPerformed

    if (!sessionController.hasActiveSession()) {
        JOptionPane.showMessageDialog(this, "Please login first!", 
            "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    ArrayList<SongModel> library = libraryController.getAllSongs();
    
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
    SongModel foundSong = null;
    for (SongModel song : library) {
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
    
    // Add to queue with validation using session controller
    QueueController queueCtrl = sessionController.getCurrentQueueController();
    QueueController.QueueResult result = queueCtrl.addToQueue(foundSong);
    
    if (result.success) {
        JOptionPane.showMessageDialog(this, 
            "'" + foundSong.getSongName() + "' by " + foundSong.getArtistName() + 
            " added to queue!", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
        updateQueueList();
    } else {
        JOptionPane.showMessageDialog(this, 
            result.message, 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }         // TODO add your handling code here:
    }//GEN-LAST:event_userQueuedAddToQueueButtonActionPerformed

    private void userQueuedPlayNextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userQueuedPlayNextButtonActionPerformed

    if (!sessionController.hasActiveSession()) {
        JOptionPane.showMessageDialog(this, "Please login first!", 
            "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    QueueController queueCtrl = sessionController.getCurrentQueueController();
    StackController stackCtrl = sessionController.getCurrentStackController();
    
    QueueController.QueueResult result = queueCtrl.playNextSong();
    
    if (result.success) {
        stackCtrl.addPlayedSong(result.song);
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
    }//GEN-LAST:event_userQueuedPlayNextButtonActionPerformed

    private void userSongsStackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userSongsStackButtonActionPerformed
        CardLayout infoLayout = (CardLayout) userCardPanel.getLayout();
    infoLayout.show(userCardPanel, "card6");
    updateRecentlyPlayedList();
    }//GEN-LAST:event_userSongsStackButtonActionPerformed

    private void userRecentlyPlayedLastPlayedButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
    if (!sessionController.hasActiveSession()) {
        JOptionPane.showMessageDialog(this, "Please login first!", 
            "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    StackController stackCtrl = sessionController.getCurrentStackController();
    SongModel lastSong = stackCtrl.getMostRecentSong();
    
    if (lastSong == null) {
        JOptionPane.showMessageDialog(this, 
            "No songs have been played yet!", 
            "Recently Played Empty", 
            JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    // Display all song details in a formatted message
    String songDetails = "LAST PLAYED SONG\n" +
                        "Song Name: " + lastSong.getSongName() + "\n" +
                        "Artist: " + lastSong.getArtistName() + "\n" +
                        "Genre: " + lastSong.getGenre() + "\n" +
                        "Album: " + lastSong.getAlbum() + "\n" +
                        "Released Year: " + lastSong.getReleasedYear();
    
    JOptionPane.showMessageDialog(this, 
        songDetails, 
        "Last Played Song Details", 
        JOptionPane.INFORMATION_MESSAGE);
}               

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
        java.awt.EventQueue.invokeLater(() -> new MusicListGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LogOutAdmin;
    private javax.swing.JPanel adminButtonsPanel;
    private javax.swing.JPanel adminCardPanel;
    private javax.swing.JButton adminHomeButton;
    private javax.swing.JLabel adminHomeLogoLabel;
    private javax.swing.JPanel adminHomePanel;
    private javax.swing.JScrollPane adminHomeScrollPane;
    private javax.swing.JTable adminHomeTable;
    private javax.swing.JLabel adminHomeTitleLabel;
    private javax.swing.JLabel adminHomeWelcomeLabel;
    private javax.swing.JButton adminLibraryAddSongButton;
    private javax.swing.JButton adminLibraryButton;
    private javax.swing.JButton adminLibraryDeleteSongButton;
    private javax.swing.JPanel adminLibraryPanel;
    private javax.swing.JScrollPane adminLibraryScrollPane;
    private javax.swing.JButton adminLibrarySearchButton;
    private javax.swing.JTextField adminLibrarySearchField;
    private javax.swing.JButton adminLibrarySortButton;
    private javax.swing.JTable adminLibraryTable;
    private javax.swing.JLabel adminLibraryTitleLabel;
    private javax.swing.JButton adminLibraryUpdateSongButton;
    private javax.swing.JPanel adminPanel;
    private javax.swing.JButton loginBoxLoginButton;
    private javax.swing.JLabel loginBoxMainTitleLabel;
    private javax.swing.JPanel loginBoxPanel;
    private javax.swing.JPasswordField loginBoxPasswordField;
    private javax.swing.JLabel loginBoxPasswordLabel;
    private javax.swing.JButton loginBoxSignInButton;
    private javax.swing.JLabel loginBoxTitleLabel;
    private javax.swing.JPanel loginBoxTitlePanel;
    private javax.swing.JTextField loginBoxUsernameField;
    private javax.swing.JLabel loginBoxUsernameLabel;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton registerBoxBackToLoginButton;
    private javax.swing.JLabel registerBoxMainTitleLabel;
    private javax.swing.JPanel registerBoxMainTitlePanel;
    private javax.swing.JPanel registerBoxPanel;
    private javax.swing.JPasswordField registerBoxPasswordField;
    private javax.swing.JLabel registerBoxPasswordLabel;
    private javax.swing.JButton registerBoxRegisterButton;
    private javax.swing.JLabel registerBoxTitleLabel;
    private javax.swing.JTextField registerBoxUsernameField;
    private javax.swing.JLabel registerBoxUsernameLabel;
    private javax.swing.JPanel registerPanel;
    private javax.swing.JPanel userButtonsPanel;
    private javax.swing.JPanel userCardPanel;
    private javax.swing.JButton userHomeButton;
    private javax.swing.JLabel userHomeLogoLabel;
    private javax.swing.JPanel userHomePanel;
    private javax.swing.JScrollPane userHomeScrollPane;
    private javax.swing.JTable userHomeTable;
    private javax.swing.JLabel userHomeTitleLabel;
    private javax.swing.JLabel userHomeWelcomeLabel;
    private javax.swing.JButton userLibraryButton;
    private javax.swing.JPanel userLibraryPanel;
    private javax.swing.JScrollPane userLibraryScrollPane;
    private javax.swing.JButton userLibrarySearchButton;
    private javax.swing.JTextField userLibrarySearchField;
    private javax.swing.JButton userLibrarySortButton;
    private javax.swing.JTable userLibraryTable;
    private javax.swing.JLabel userLibraryTitleLabel;
    private javax.swing.JButton userLogOutButton;
    private javax.swing.JPanel userPanel;
    private javax.swing.JButton userPlaylistAddSongButton;
    private javax.swing.JButton userPlaylistButton;
    private javax.swing.JPanel userPlaylistPanel;
    private javax.swing.JButton userPlaylistRemoveSongButton;
    private javax.swing.JScrollPane userPlaylistScrollPane;
    private javax.swing.JTable userPlaylistTable;
    private javax.swing.JLabel userPlaylistTitleLabel;
    private javax.swing.JButton userQueuedAddToQueueButton;
    private javax.swing.JList<String> userQueuedList;
    private javax.swing.JButton userQueuedPlayNextButton;
    private javax.swing.JScrollPane userQueuedScrollPane;
    private javax.swing.JPanel userQueuedSongsPanel;
    private javax.swing.JLabel userQueuedTitleLabel;
    private javax.swing.JButton userRecentlyPlayedLastPlayedButton;
    private javax.swing.JPanel userRecentlyPlayedPanel;
    private javax.swing.JScrollPane userRecentlyPlayedScrollPane;
    private javax.swing.JLabel userRecentlyPlayedSubTitleLabel;
    private javax.swing.JList<String> userRecentlyPlayedTable;
    private javax.swing.JLabel userRecentlyPlayedTitleLabel;
    private javax.swing.JButton userSongsQueueButton;
    private javax.swing.JButton userSongsStackButton;
    // End of variables declaration//GEN-END:variables
}
