package com.mygdx.game;

public class ScoreHandler {
    private String winner;
    private static boolean xWin, yWin, tie;
    private static Tile[][] grid;

    public ScoreHandler() {
        initialize();
    }

    public static void update() {
        grid = Board.getGrid();
        checkRow();
        checkColumn();
        checkDiagonal();
        System.out.println("xwin " + xWin + " ywin " + yWin);
        if (!xWin && !yWin) {
            checkTie();
        }

    }

    public static void initialize() {
        xWin = yWin = tie = false;
    }

    public static boolean isGameOver() {
        return xWin || yWin || tie;
    }

    public static void checkTie() {
        for (Tile[] tiles : grid) {
            for (Tile tile : tiles) {
                if (tile.getPieceType() == 'e') {
                    tie = false;
                    return;
                }
            }
        }
        tie = true;
    }

    public static void checkRow() {
        for (Tile[] tiles : grid) {
            if (tiles[0].getPieceType() != 'e' && tiles[0].getPieceType() == tiles[1].getPieceType() && tiles[0].getPieceType() == tiles[2].getPieceType()) {
                if (tiles[0].getPieceType() == 'X') {
                    xWin = true;
                    yWin = false;
                } else {
                    xWin = false;
                    yWin = true;
                }
                return;
            }
        }
    }

    public static void checkColumn() {
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i].getPieceType() != 'e' && grid[0][i].getPieceType() == grid[1][i].getPieceType() && grid[0][i].getPieceType() == grid[2][i].getPieceType()) {
                if (grid[0][i].getPieceType() == 'X') {
                    xWin = true;
                    yWin = false;
                } else {
                    xWin = false;
                    yWin = true;
                }
                tie = false;
                return;
            }
        }
    }

    public static boolean checkDiagonal() {
        if (grid[0][0].getPieceType() != 'e' && grid[0][0].getPieceType() == grid[1][1].getPieceType() && grid[0][0].getPieceType() == grid[2][2].getPieceType()) {
            if (grid[0][0].getPieceType() == 'X') {
                xWin = true;
                yWin = false;
            } else {
                xWin = false;
                yWin = true;
            }
            tie = false;
            return true;
        }
        if (grid[0][2].getPieceType() != 'e' && grid[0][2].getPieceType() == grid[1][1].getPieceType() && grid[0][2].getPieceType() == grid[2][0].getPieceType()) {
            if (grid[0][2].getPieceType() == 'X') {
                xWin = true;
                yWin = false;
            } else {
                xWin = false;
                yWin = true;
            }
            tie = false;
            return true;
        }

        return false;
    }

    public static String getWinningString() {
        if (tie) {
            System.out.println(tie);
            return "Tie";
        } else if (yWin) {
            return "0 Won!!";
        } else if (xWin) {
            return "X Won!!";
        } else {
            return "";
        }
    }
}
