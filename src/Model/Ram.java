package Model;

public class Ram extends Pieces {
    private boolean moveForward = true; // Indicates the direction of the Ram

    public Ram(int x, int y, String label) {
        super(x, y, label);
    }

    @Override
    public void Move(int x, int y, int newX, int newY) {
        if (isValid(x, y, newX, newY)) {
            setX(newX);
            setY(newY);

            // Check if the Ram reached the end of the board and needs to turn around
            if (moveForward && newY == 7) { // Board has 8 rows, 0-7
                moveForward = false;
            } else if (!moveForward && newY == 0) {
                moveForward = true;
            }
        }
    }

    @Override
    public boolean isValid(int x, int y, int newX, int newY) {
        if (newX != x) {
            return false; // The Ram can only move vertically
        }

        int step = moveForward ? 1 : -1;
        return (newY - y) == step; // Check if the move is exactly one step in the correct direction
    }
}
