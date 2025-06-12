package contract;

import model.Element;
import model.Position;
import util.ListArray;

public interface IAttack {
    Position Attacking(ListArray<Position> posibleCellAction, ListArray<Element> enemys);
}
