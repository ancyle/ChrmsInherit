package user.ancyle.chrms.business.abstracts;

import user.ancyle.chrms.core.utilities.result.DataResult;
import user.ancyle.chrms.core.utilities.result.Result;
import user.ancyle.chrms.entities.concretes.Position;

import java.util.List;

public interface PositionService {
    public Result newPosition(Position position);
    public DataResult<List<Position>> listAllPositions();
    public DataResult<Position> getPosition(short id);
    public Result updatePosition(Position position);
    public Result deletePositionById(short id);
}
