package com.edu.nyu.cs.pqs.pqs5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * The listener class which contains the logic for the UI elements
 * 
 * @author pv594
 *
 */
public class GameUI implements GameListener {

  private GameTypeFactory GameFactory;
  private GameModel GameType;
  private JFrame frame = new JFrame();
  private JButton startVsComputer = new JButton("Start Vs Computer");
  private JButton startVsPlayer = new JButton("Start Vs Player");
  private JButton restart = new JButton("Restart Game");
  private JButton[] columnSelectButtons = new JButton[7];
  private JLabel statusNextPlayer;
  private JLabel statusWinner;
  private JLabel statusGameMode;
  private JLabel[][] boardLabelArray = new JLabel[6][7];
  private JPanel board = new JPanel(new GridLayout(7, 7, 1, 1));
  private JPanel startPanel = new JPanel(new GridLayout(1, 3));
  private JPanel gameStatusPanel = new JPanel(new GridLayout(1, 3));
  private ImageIcon tile1 = new ImageIcon(getClass().getResource(
      "Empty_Tile.png"));
  private ImageIcon tile2 = new ImageIcon(getClass()
      .getResource("Red_Tile.png"));
  private ImageIcon tile3 = new ImageIcon(getClass().getResource(
      "Yellow_Tile.png"));
  private ImageIcon arrow = new ImageIcon(getClass().getResource("arrow2.png"));

  private class ColumnSelectActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      short column = Short.parseShort(e.getActionCommand());
      columnButtonPressed(column);
    }
  };

  private class StartGameActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String playMode = e.getActionCommand();
      if (!playMode.equals("Restart")) {
        startButtonPressed(playMode);
      } else {
        restartButtonPressed();
      }
    }
  };

  GameUI(GameTypeFactory GameFactory) {
    this.GameFactory = GameFactory;
    frame.setLayout(new BorderLayout());
    frame.setTitle("Connect 4 - Prakhar Verma");
    frame.setResizable(false);

    // panel for start game
    startVsComputer.setActionCommand("Vs Computer");
    startVsComputer.addActionListener(new StartGameActionListener());
    startVsPlayer.setActionCommand("Vs Player");
    startVsPlayer.addActionListener(new StartGameActionListener());
    restart.setActionCommand("Restart");
    restart.addActionListener(new StartGameActionListener());
    startPanel.setBackground(Color.RED);
    startPanel.setOpaque(true);
    startPanel.add(startVsComputer);
    startPanel.add(startVsPlayer);
    startPanel.add(restart);

    // status panel
    statusGameMode = new JLabel("GAME MODE:");
    statusNextPlayer = new JLabel("PLAYER TURN:");
    statusWinner = new JLabel("WINNER:");
    gameStatusPanel.add(statusGameMode);
    gameStatusPanel.add(statusNextPlayer);
    gameStatusPanel.add(statusWinner);

    // start button at top of page and add action listeners
    frame.add(startPanel, BorderLayout.PAGE_START);

    // create a panel which will contain the 7x7 board with first row containing
    // the column selector button
    board.setBackground(Color.ORANGE);
    board.setOpaque(true);

    // initialise button array with button objects and client properties, then
    // add to board panel
    for (int i = 0; i < 7; i++) {
      columnSelectButtons[i] = new JButton(arrow);
      columnSelectButtons[i].setActionCommand("" + i);
      columnSelectButtons[i]
          .addActionListener(new ColumnSelectActionListener());
      columnSelectButtons[i].setEnabled(false);
      // add buttons to the column select panel
      board.add(columnSelectButtons[i]);
    }

    // initialise board label array with label objects and client properties,
    // then add to board panel
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        boardLabelArray[i][j] = new JLabel(tile1);
        board.add(boardLabelArray[i][j]);
      }
    }

    frame.add(gameStatusPanel, BorderLayout.PAGE_END);
    frame.add(board, BorderLayout.CENTER);
    frame.pack();
    frame.setSize(715, 680);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  // function for the buttons - start game, stop game, restart game and column
  // select buttons
  /**
   * Method that informs the back end that a column has been clicked and passes
   * the associated column identifier information to it
   * 
   * @param short column
   */
  public void columnButtonPressed(short column) {
    GameType.gameController(column);
  }

  /**
   * Method that informs the back end that the start game button has been
   * clicked and passes the associated Game Mode information to it
   * 
   * @param String
   *          gameMode
   */
  public void startButtonPressed(String gameMode) {
    GameType = GameFactory.GetGameBackend(gameMode);
    GameType.registerListener(this);
    GameType.startGame();
  }

  /**
   * Method that informs the back end that the restart button has been clicked
   */
  public void restartButtonPressed() {
    GameType.gameReset();
  }

  /**
   * Method that starts the game and updates the UI by enabling the column input
   * buttons.
   * 
   * @param gameState
   */
  @Override
  public void gameStart(GameState gameState) {
    updateGameStatus(gameState);
    for (int i = 0; i < 7; i++) {
      columnSelectButtons[i].setEnabled(true);
    }
  }

  /**
   * Method to update the board on the UI. Accepts the last move played as
   * parameter
   * 
   * @param GameMove
   *          LastMovePlayed
   */
  @Override
  public void updateBoard(GameMove LastMovePlayed) {
    short row = LastMovePlayed.getRow();
    short column = LastMovePlayed.getColumn();
    char pawn = LastMovePlayed.getPawn();

    if (pawn == 'A') {
      boardLabelArray[row][column].setIcon(tile2);
    } else if (pawn == 'B') {
      boardLabelArray[row][column].setIcon(tile3);
    }
  }

  /**
   * Method that upates the game status information on the UI. Accepts game
   * state instance as parameter
   * 
   * @param GameState
   *          gameState
   */
  @Override
  public void updateGameStatus(GameState gameState) {
    String gameMode = gameState.getGameMode();
    char playerTurn = gameState.getTurn();
    char winner = gameState.getWinner();
    statusGameMode.setText("GAME MODE: " + gameState.getGameMode());
    if (gameMode.equals("Vs Computer") && playerTurn == 'B') {
      statusNextPlayer.setText("PLAYER TURN: Computer");
    } else {
      statusNextPlayer.setText("PLAYER TURN: " + playerTurn);
    }
    if (winner == 'A') {
      statusWinner.setText("WINNER: Player A wins!");
    } else if (winner == 'B' && gameMode.equals("Vs Computer")) {
      statusWinner.setText("WINNER: Computer wins!");
    } else if (winner == 'B' && gameMode.equals("Vs Player")) {
      statusWinner.setText("WINNER: Player B wins!");
    } else {
      statusWinner.setText("WINNER: No winner yet");
    }
  }

  /**
   * Method that updates the UI when the game ends
   */
  @Override
  public void gameEnd() {
    for (int i = 0; i < 7; i++) {
      columnSelectButtons[i].setEnabled(false);
    }
    startVsPlayer.setEnabled(false);
    startVsComputer.setEnabled(false);
  }

  /**
   * Method to reset the UI elements when the game is reset and a new game is
   * started
   */
  @Override
  public void resetGame() {
    // reset board
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        boardLabelArray[i][j].setIcon(tile1);
      }
    }
    // reset start buttons
    startVsPlayer.setEnabled(true);
    startVsComputer.setEnabled(true);
    // reset status Bar
    statusGameMode.setText("GAME MODE:");
    statusNextPlayer.setText("PLAYER TURN:");
    statusWinner.setText("WINNER:");
  }

}
