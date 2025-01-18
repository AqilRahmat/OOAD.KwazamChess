package Model;

public class RamModel extends Pieces {
    private int countup = 1;
    private int countdown = 1;
    private boolean moveUp = false;

    public RamModel(int row, int col, BoardModel board) {super(row, col, board);}

    @Override
    public void Move(int newRow, int newCol) {
        if (isValid(newRow, newCol)) {
            super.Move(newRow, newCol);
            System.out.println(countup + countdown);

            if(newRow < getRow()) {
                if(countup < 6) {
                    countup++;
                    countdown = 1;
                } else {
                    moveUp = true;
                }
            } else if(newRow > getRow()) {
                if(moveUp) {
                    countdown++;
                    countup = 1;
                } else {
                    return;
                }
            }
        }

        if(countup == 6) {
            countdown = 1;
            moveUp = true;
        } else if(countdown == 7) {
            countup = 1;
            moveUp = false;
        }
    }

    @Override
    public boolean isValid(int newRow, int newCol) {
        if(newCol != getCol()) {
            return false;
        }

        if(countup < 6 && newRow == getRow() - 1) {
            return true;
        } else if(countdown < 7 && newRow == getRow() + 1) {
            return true;
        }

        return false;
    }
}
