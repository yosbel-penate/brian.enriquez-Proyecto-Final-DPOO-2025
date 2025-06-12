package contract;

import model.Position;
import util.ListArray;

public interface IMove {
    Character Moving(ListArray<Position> movs, Position posPuzle);
}
