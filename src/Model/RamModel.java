package Model;

public class RamModel extends Pieces {
    private int countup; // Tracks upward movement steps
    private boolean reachedEnd = false; // Tracks if the end has been reached
    private boolean isFlipped = false;  // Tracks if the piece has flipped (direction change)

    public RamModel(int row, int col, BoardModel board) {
        super(row, col, board);
    }

    @Override
    public void Move(int newRow, int newCol) {
        // If valid move, make the move and update the state
        if (isValid(newRow, newCol)) {
            super.Move(newRow, newCol);

            // Update the countup based on direction
            if (!isFlipped) {
                if (newRow < getRow()) {
                    countup++;
                }
                if (newRow == 0) {
                    reachedEnd = true; // The piece has reached the end
                    isFlipped = true; // Flip the piece (change direction)
                }
            } else {
                if (newRow > getRow()) {
                    countup--;
                }
            }
        }
    }

    @Override
    public boolean isValid(int newRow, int newCol) {
        // Only allow vertical movement in the same column
        if (newCol != getCol()) {
            return false;
        }

        // If piece has reached the end and hasn't flipped, it can't move anymore
        if (reachedEnd && !isFlipped) {
            return false; // Can't move anymore if we have reached the end without flipping
        }

        // Handle valid moves based on whether the piece has flipped or not
        if (!isFlipped) {
            // Moving upwards (toward the opponent's side)
            if (countup < 6 && newRow == getRow() - 1) {
                return true; // Valid move upwards
            }
        } else {
            // Moving downwards (backwards)
            if (newRow == getRow() + 1) {
                return true; // Valid move downward (after flip)
            }
        }

        return false; // Otherwise, invalid move
    }
}